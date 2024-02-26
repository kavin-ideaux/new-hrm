package com.example.HRM.controller.organization;
import com.example.HRM.entity.organization.Announcements;
import com.example.HRM.repository.organization.AnnouncementsRepository;
import com.example.HRM.service.organization.AnnouncementsService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class AnnouncementsController {
	@Autowired
	  private AnnouncementsService service;
	  
	  @Autowired
	  private AnnouncementsRepository repo;
	  
	  @GetMapping({"/announcement"})
	  public ResponseEntity<?> displayAllImages(@RequestParam(required = true) String announcementType) {
	    try {
	      if ("announcement".equals(announcementType)) {
	        List<Announcements> announcements = this.service.listAll();
	        List<Announcements> announcementResponses = new ArrayList<>();
	        for (Announcements announcement : announcements) {
	          int randomNumber = generateRandomNumber();
	          String fileExtension = getFileExtensionForImage(announcement);
	          String imageUrl = "announcement/" + randomNumber + "/" + announcement.getEmployeeId() + "." + fileExtension;
	          Announcements announcementResponse = new Announcements();
	          announcementResponse.setAnnouncementId(announcement.getAnnouncementId());
	          announcementResponse.setUrl(imageUrl);
	          announcementResponse.setFromDate(announcement.getFromDate());
	          announcementResponse.setToDate(announcement.getToDate());
	          announcementResponse.setTitle(announcement.getTitle());
	          announcementResponse.setInformedBy(announcement.getInformedBy());
	          announcementResponse.setPublished(announcement.isPublished());
	          announcementResponse.setStatus(announcement.isStatus());
	          announcementResponses.add(announcementResponse);
	        } 
	        return ResponseEntity.ok().body(announcementResponses);
	      } 
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body("The provided announcementType is not supported.");
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PostMapping({"/announcement/save"})
	  public ResponseEntity<String> addAnnouncementWithImage(@RequestParam("attachment") MultipartFile file, @RequestParam("title") String title, @RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, @RequestParam("informedBy") String email, @RequestParam("published") boolean published) {
	    try {
	      byte[] bytes = file.getBytes();
	      Blob blob = new SerialBlob(bytes);
	      Announcements announcement = new Announcements();
	      announcement.setAttachment(blob);
	      announcement.setTitle(title);
	      announcement.setFromDate(fromDate);
	      announcement.setToDate(toDate);
	      announcement.setInformedBy(email);
	      announcement.setPublished(published);
	      if (announcement.getFromDate() != null && announcement.getToDate() != null && announcement
	        .getFromDate().after(announcement.getToDate())) {
	        String errorMessage = "FromDate cannot be later than ToDate.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	      } 
	      announcement.setStatus(true);
	      this.service.SaveorUpdate(announcement);
	      return ResponseEntity.status(HttpStatus.CREATED).body("Announcement details saved successfully.");
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding employee.");
	    } 
	  }
	  
	  @RequestMapping({"/announcement/{announcementId}"})
	  private Optional<Announcements> getAnnouncement(@PathVariable(name = "announcementId") long announcementId) {
	    return this.service.getAnnouncementsById(Long.valueOf(announcementId));
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"announcement/{randomNumber}/{id:.+}"})
	  public ResponseEntity<Resource> serveImage(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") String id) {
	    Long imageId;
	    byte[] imageBytes;
	    String[] parts = id.split("\\.");
	    if (parts.length != 2)
	      return ResponseEntity.badRequest().build(); 
	    String fileExtension = parts[1];
	    try {
	      imageId = Long.valueOf(Long.parseLong(parts[0]));
	    } catch (NumberFormatException e) {
	      return ResponseEntity.badRequest().build();
	    } 
	    Announcements image = this.service.findById(imageId);
	    if (image == null)
	      return ResponseEntity.notFound().build(); 
	    try {
	      imageBytes = image.getAttachment().getBytes(1L, (int)image.getAttachment().length());
	    } catch (SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    ByteArrayResource resource = new ByteArrayResource(imageBytes);
	    HttpHeaders headers = new HttpHeaders();
	    if ("jpg".equalsIgnoreCase(fileExtension)) {
	      headers.setContentType(MediaType.IMAGE_JPEG);
	    } else if ("png".equalsIgnoreCase(fileExtension)) {
	      headers.setContentType(MediaType.IMAGE_PNG);
	    } else {
	      headers.setContentType(MediaType.IMAGE_JPEG);
	    } 
	    return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	  }
	  
	  private String getFileExtensionForImage(Announcements image) {
	    if (image == null || image.getUrl() == null || image.getUrl().isEmpty())
	      return "jpg"; 
	    String url = image.getUrl();
	    if (url.endsWith(".png"))
	      return "png"; 
	    if (url.endsWith(".jpg"))
	      return "jpg"; 
	    return "jpg";
	  }
	  
	  @PutMapping({"/announcement/or/{announcement_id}"})
	  public ResponseEntity<Boolean> toggleCustomerStatus(@PathVariable(name = "announcement_id") long announcement_id) {
	    try {
	      Announcements announcement = this.service.findById(Long.valueOf(announcement_id));
	      if (announcement != null) {
	        boolean currentStatus = announcement.isStatus();
	        announcement.setStatus(!currentStatus);
	        this.service.SaveorUpdate(announcement);
	      } else {
	        return ResponseEntity.ok(Boolean.valueOf(false));
	      } 
	      return ResponseEntity.ok(Boolean.valueOf(announcement.isStatus()));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body(Boolean.valueOf(false));
	    } 
	  }
	  
	  @PutMapping({"/announcement/edit/{announcementId}"})
	  public ResponseEntity<?> updateAnnouncement(@PathVariable Long announcementId, @RequestParam("attachment") MultipartFile file, @RequestParam("title") String title, @RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, @RequestParam("informedBy") String email, @RequestParam("published") boolean published) {
	    try {
	      Announcements existingAnnouncement = this.service.findById(announcementId);
	      if (existingAnnouncement == null)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found."); 
	      existingAnnouncement.setTitle(title);
	      existingAnnouncement.setFromDate(fromDate);
	      existingAnnouncement.setToDate(toDate);
	      existingAnnouncement.setInformedBy(email);
	      existingAnnouncement.setPublished(published);
	      if (file != null && !file.isEmpty()) {
	        byte[] bytes = file.getBytes();
	        Blob blob = new SerialBlob(bytes);
	        existingAnnouncement.setAttachment(blob);
	      } 
	      if (existingAnnouncement.getFromDate() != null && existingAnnouncement.getToDate() != null && existingAnnouncement
	        .getFromDate().after(existingAnnouncement.getToDate())) {
	        String errorMessage = "FromDate cannot be later than ToDate.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	      } 
	      this.service.save(existingAnnouncement);
	      return ResponseEntity.ok(existingAnnouncement);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee.");
	    } 
	  }
	  
	  @DeleteMapping({"/announcement/announcementdelete/{announcementId}"})
	  public ResponseEntity<String> deleteTitle(@PathVariable("announcementId") Long announcementId) {
	    this.service.deleteAnnouncementsIdById(announcementId);
	    return ResponseEntity.ok("announcement deleted successfully");
	  }
	}


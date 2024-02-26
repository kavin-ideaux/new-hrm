package com.example.HRM.controller.employee;
import com.example.HRM.entity.employee.Awards;
import com.example.HRM.entity.employee.AwardsPhoto;
import com.example.HRM.repository.employee.AwardsRepository;
import com.example.HRM.service.employee.AwardsPhotoService;
import com.example.HRM.service.employee.AwardsService;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class AwardsController {
	@Autowired
	  private AwardsService service;
	  
	  @Autowired
	  private AwardsRepository repo;
	  
	  @Autowired
	  private AwardsPhotoService awardsPhotoService;
	  
	  @PostMapping(value = {"/awards/save"}, headers = {"content-type=multipart/form-data"}, consumes = {"multipart/form-data"})
	  public ResponseEntity<String> addImagePost(@RequestPart("awardsPhoto") MultipartFile[] files, @RequestParam("description") String description, @RequestParam("date") Date date, @RequestParam("awardsType") String awardsType, @RequestParam("employeeId") long employeeId) {
	    try {
	      if (files == null || files.length == 0)
	        return ResponseEntity.badRequest().body("No files provided"); 
	      List<AwardsPhoto> awardsPhotos = new ArrayList<>();
	      for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	          byte[] bytes = file.getBytes();
	          Blob blob = new SerialBlob(bytes);
	          AwardsPhoto awardsPhoto = new AwardsPhoto();
	          awardsPhoto.setAwardsPhoto(blob);
	          awardsPhotos.add(awardsPhoto);
	        } 
	      } 
	      Awards awards = new Awards();
	      awards.setDescription(description);
	      awards.setAwardsType(awardsType);
	      awards.setDate(date);
	      awards.setStatus(true);
	      awards.setEmployeeId(employeeId);
	      awards.setAwardsPhotos(awardsPhotos);
	      this.service.create(awards);
	      long id = awards.getAwardsId();
	      return ResponseEntity.ok("Images added successfully. Awards ID: " + id);
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error adding images: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/awards/{awardsId}"})
	  public ResponseEntity<Awards> getAwardsById56(@PathVariable("awardsId") long awardsId) {
	    try {
	      Optional<Awards> awardsOptional = this.service.getAwardsById(awardsId);
	      if (awardsOptional.isPresent()) {
	        Awards awards = awardsOptional.get();
	        List<AwardsPhoto> awardsPhotos = awards.getAwardsPhotos();
	        for (AwardsPhoto awardsPhoto : awardsPhotos) {
	          String imageUrl = "/image/" + generateRandomNumber() + "/" + awardsPhoto.getAwardsPhotoId();
	          awardsPhoto.setUrl(imageUrl);
	        } 
	        return ResponseEntity.ok(awards);
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @PutMapping({"/awards/or/{awardsId}"})
	  public ResponseEntity<Awards> toggleAwardsStatusAndSetPhotoUrls(@PathVariable("awardsId") long awardsId) {
	    try {
	      Optional<Awards> awardsOptional = this.service.getAwardsById(awardsId);
	      if (awardsOptional.isPresent()) {
	        Awards awards = awardsOptional.get();
	        boolean currentStatus = awards.isStatus();
	        awards.setStatus(!currentStatus);
	        this.service.update(awards);
	        List<AwardsPhoto> awardsPhotos = awards.getAwardsPhotos();
	        for (AwardsPhoto awardsPhoto : awardsPhotos) {
	          String imageUrl = "/image/" + generateRandomNumber() + "/" + awardsPhoto.getAwardsPhotoId();
	          awardsPhoto.setUrl(imageUrl);
	        } 
	        return ResponseEntity.ok(awards);
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  private int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	  }
	  
	  @GetMapping({"/awards"})
	  public ResponseEntity<?> getAllAwards(@RequestParam(required = true) String awardsParam) {
	    try {
	      if ("allAwards".equals(awardsParam)) {
	        List<Awards> awardsList = this.service.getAllAwards();
	        for (Awards award : awardsList) {
	          List<AwardsPhoto> awardsPhotos = award.getAwardsPhotos();
	          for (AwardsPhoto awardsPhoto : awardsPhotos) {
	            String imageUrl = "/image/" + generateRandomNumber() + "/" + awardsPhoto.getAwardsPhotoId();
	            awardsPhoto.setUrl(imageUrl);
	          } 
	        } 
	        return ResponseEntity.ok(awardsList);
	      } 
	      String errorMessage = "The provided awards parameter is not supported.";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	  }
	  
	  @GetMapping({"/image/{randomNumber}/{id}"})
	  public ResponseEntity<Resource> serveFile(@PathVariable("randomNumber") int randomNumber, @PathVariable("id") Long id) {
	    Optional<AwardsPhoto> awardsPhotoOptional = this.awardsPhotoService.getAwardsPhotoById(id.longValue());
	    if (awardsPhotoOptional.isPresent()) {
	      byte[] fileBytes;
	      AwardsPhoto awardsPhoto = awardsPhotoOptional.get();
	      String filename = "file_" + randomNumber + "_" + id;
	      try {
	        fileBytes = awardsPhoto.getAwardsPhoto().getBytes(1L, (int)awardsPhoto.getAwardsPhoto().length());
	      } catch (SQLException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	      } 
	      String extension = determineFileExtension(fileBytes);
	      MediaType mediaType = determineMediaType(extension);
	      ByteArrayResource resource = new ByteArrayResource(fileBytes);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(mediaType);
	      headers.set("Content-Disposition", "inline; filename=" + filename + "." + extension);
	      return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().headers(headers)).body(resource);
	    } 
	    return ResponseEntity.notFound().build();
	  }
	  
	  private String determineFileExtension(byte[] fileBytes) {
	    try {
	      String fileSignature = bytesToHex(Arrays.copyOfRange(fileBytes, 0, 4));
	      if (fileSignature.startsWith("89504E47"))
	        return "png"; 
	      if (fileSignature.startsWith("FFD8FF"))
	        return "jpg"; 
	      if (fileSignature.startsWith("25504446"))
	        return "pdf"; 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return "unknown";
	  }
	  
	  private MediaType determineMediaType(String extension) {
	    switch (extension) {
	      case "png":
	        return MediaType.IMAGE_PNG;
	      case "jpg":
	        return MediaType.IMAGE_JPEG;
	      case "pdf":
	        return MediaType.APPLICATION_PDF;
	    } 
	    return MediaType.APPLICATION_OCTET_STREAM;
	  }
	  
	  private String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	      sb.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));
	    } 
	    return sb.toString();
	  }
	  
	  @PutMapping({"/edit/{awardsId}"})
	  public ResponseEntity<String> editImage(@PathVariable("awardsId") long awardsId, @RequestParam(value = "awardsPhoto", required = false) MultipartFile[] files, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "date", required = false) Date date, @RequestParam(value = "employeeId", required = false) long employeeId, @RequestParam(value = "awardsType", required = false) String awardsType) {
	    try {
	      Optional<Awards> awardsOptional = this.service.getAwardsById(awardsId);
	      if (awardsOptional.isPresent()) {
	        Awards awards = awardsOptional.get();
	        if (description != null)
	          awards.setDescription(description); 
	        if (awardsType != null)
	          awards.setAwardsType(awardsType); 
	        if (date != null)
	          awards.setDate(date); 
	        if (employeeId != 0L)
	          awards.setEmployeeId(employeeId); 
	        if (files != null && files.length > 0) {
	          List<AwardsPhoto> awardsPhotos = new ArrayList<>();
	          for (MultipartFile file : files) {
	            byte[] bytes = file.getBytes();
	            Blob blob = new SerialBlob(bytes);
	            AwardsPhoto awardsPhoto = new AwardsPhoto();
	            awardsPhoto.setAwardsPhoto(blob);
	            awardsPhotos.add(awardsPhoto);
	          } 
	          awards.setAwardsPhotos(awardsPhotos);
	        } 
	        this.service.update(awards);
	        String imageUrl = "/image/" + generateRandomNumber() + "/" + awards.getAwardsId();
	        return ResponseEntity.ok("Images and awards details updated successfully. Awards ID: " + awardsId);
	      } 
	      return ResponseEntity.notFound().build();
	    } catch (IOException|SQLException e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        .body("Error editing images: " + e.getMessage());
	    } 
	  }
	  
	  @GetMapping({"/photo"})
	  public List<Map<String, Object>> allCompanyDetails(@RequestParam(required = true) String awardsParam) {
	    if ("allPhoto".equals(awardsParam)) {
	      List<Map<String, Object>> awardList = new ArrayList<>();
	      List<Map<String, Object>> awardDetails = this.repo.AllEmployee();
	      Map<String, Map<String, Object>> awardsMap = new HashMap<>();
	      for (Map<String, Object> award : awardDetails) {
	        String awardsId = award.get("awards_id").toString();
	        if (!awardsMap.containsKey(awardsId)) {
	          Map<String, Object> awardsData = new HashMap<>();
	          awardsData.put("awardsId", award.get("awards_id"));
	          awardsData.put("description", getValueOrNull(award.get("description")));
	          awardsData.put("date", getValueOrNull(award.get("date")));
	          awardsData.put("status", getValueOrNull(award.get("status")));
	          awardsData.put("employeeId", getValueOrNull(award.get("employee_id")));
	          awardsData.put("userName", getValueOrNull(award.get("user_name")));
	          awardsData.put("awardsType", getValueOrNull(award.get("awards_type")));
	          List<Map<String, Object>> awardsPhotosList = new ArrayList<>();
	          awardsData.put("awardsPhotos", awardsPhotosList);
	          awardsMap.put(awardsId, awardsData);
	          awardList.add(awardsData);
	        } 
	        if (award.get("awards_photo_id") != null) {
	          Map<String, Object> awardsPhotoMap = new HashMap<>();
	          awardsPhotoMap.put("awardsPhotoId", award.get("awards_photo_id"));
	          awardsPhotoMap.put("url", "/image/" + generateRandomNumber() + "/" + award.get("awards_photo_id"));
	          List<Map<String, Object>> photosList = (List<Map<String, Object>>)((Map)awardsMap.get(awardsId)).get("awardsPhotos");
	          if (photosList == null) {
	            photosList = new ArrayList<>();
	            awardsPhotoMap.put("awardsPhotos", photosList);
	          } 
	          photosList.add(awardsPhotoMap);
	        } 
	      } 
	      return awardList;
	    } 
	    throw new IllegalArgumentException("The provided awards parameter is not supported.");
	  }
	  
	  private Object getValueOrNull(Object value) {
	    return (value != null && !value.toString().trim().isEmpty()) ? value : null;
	  }
	  
	  @GetMapping({"/awards/employee/{employee_id}"})
	  public List<Map<String, Object>> allCompanyDetails(@PathVariable("employee_id") Long employee_id) {
	    List<Map<String, Object>> awardList = new ArrayList<>();
	    List<Map<String, Object>> awardDetails = this.repo.AllfilterID(employee_id.longValue());
	    Map<String, Map<String, Object>> awardsMap = new HashMap<>();
	    for (Map<String, Object> award : awardDetails) {
	      String awardsId = award.get("awards_id").toString();
	      if (!awardsMap.containsKey(awardsId)) {
	        Map<String, Object> awardsData = new HashMap<>();
	        awardsData.put("awardsId", award.get("awards_id"));
	        awardsData.put("description", getValueOrNull(award.get("description")));
	        awardsData.put("date", getValueOrNull(award.get("date")));
	        awardsData.put("status", getValueOrNull(award.get("status")));
	        awardsData.put("employeeId", getValueOrNull(award.get("employee_id")));
	        awardsData.put("userId", getValueOrNull(award.get("user_id")));
	        awardsData.put("userName", getValueOrNull(award.get("user_name")));
	        awardsData.put("awardsType", getValueOrNull(award.get("awards_type")));
	        List<Map<String, Object>> awardsPhotosList = new ArrayList<>();
	        awardsData.put("awardsPhotos", awardsPhotosList);
	        awardsMap.put(awardsId, awardsData);
	        awardList.add(awardsData);
	      } 
	      if (award.get("awards_photo_id") != null) {
	        Map<String, Object> awardsPhotoMap = new HashMap<>();
	        awardsPhotoMap.put("awardsPhotoId", award.get("awards_photo_id"));
	        awardsPhotoMap.put("url", "/image/" + generateRandomNumber() + "/" + award.get("awards_photo_id"));
	        List<Map<String, Object>> photosList = (List<Map<String, Object>>)((Map)awardsMap.get(awardsId)).get("awardsPhotos");
	        if (photosList == null) {
	          photosList = new ArrayList<>();
	          awardsPhotoMap.put("awardsPhotos", photosList);
	        } 
	        photosList.add(awardsPhotoMap);
	      } 
	    } 
	    return awardList;
	  }
	  
	  @GetMapping({"/awards/count/{employee_id}"})
	  private Map<String, Object> getTotalAwardsCount(@PathVariable("employee_id") Long employee_id) {
	    List<Map<String, Object>> awardsList = this.repo.Allfilter(employee_id.longValue());
	    Set<BigInteger> uniqueAwards = new HashSet<>();
	    for (Map<String, Object> award : awardsList) {
	      BigInteger awardIdBigInt = (BigInteger)award.get("awards_id");
	      uniqueAwards.add(awardIdBigInt);
	    } 
	    int totalAwardsCount = uniqueAwards.size();
	    Map<String, Object> result = new HashMap<>();
	    result.put("total_awards", Integer.valueOf(totalAwardsCount));
	    return result;
	  }
	  
	  @PostMapping({"/awards/date"})
	  public List<Map<String, Object>> getEmployeeAwardsByDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
	    List<Map<String, Object>> awardsList = new ArrayList<>();
	    List<Map<String, Object>> awards = this.repo.findAwardsByEmployeeIdAndDate(startDate, endDate);
	    Map<String, Map<String, Object>> awardsMap = new HashMap<>();
	    for (Map<String, Object> award : awards) {
	      String awardsId = award.get("awards_id").toString();
	      if (!awardsMap.containsKey(awardsId)) {
	        Map<String, Object> awardsData = new HashMap<>();
	        awardsData.put("awardsId", award.get("awards_id"));
	        awardsData.put("description", getValueOrNull(award.get("description")));
	        awardsData.put("date", getValueOrNull(award.get("date")));
	        awardsData.put("status", getValueOrNull(award.get("status")));
	        awardsData.put("employeeId", getValueOrNull(award.get("employee_id")));
	        awardsData.put("userName", getValueOrNull(award.get("user_name")));
	        awardsData.put("userId", getValueOrNull(award.get("user_id")));
	        awardsData.put("awardsType", getValueOrNull(award.get("awards_type")));
	        List<Map<String, Object>> awardsPhotosList = new ArrayList<>();
	        awardsData.put("awardsPhotos", awardsPhotosList);
	        awardsMap.put(awardsId, awardsData);
	        awardsList.add(awardsData);
	      } 
	      if (award.get("awards_photo_id") != null) {
	        Map<String, Object> awardsPhotoMap = new HashMap<>();
	        awardsPhotoMap.put("awardsPhotoId", award.get("awards_photo_id"));
	        awardsPhotoMap.put("url", "/image/" + generateRandomNumber() + "/" + award.get("awards_photo_id"));
	        List<Map<String, Object>> photosList = (List<Map<String, Object>>)((Map)awardsMap.get(awardsId)).get("awardsPhotos");
	        if (photosList == null) {
	          photosList = new ArrayList<>();
	          awardsPhotoMap.put("awardsPhotos", photosList);
	        } 
	        photosList.add(awardsPhotoMap);
	      } 
	    } 
	    return awardsList;
	  }
	  
	  @GetMapping({"/awards/count"})
	  public List<Map<String, Object>> getEmployeeAwardsCount() {
	    List<Object[]> results = this.repo.getEmployeeAwardsCount();
	    List<Map<String, Object>> employeeAwardsList = new ArrayList<>();
	    for (Object[] result : results) {
	      Long employeeId = Long.valueOf(((BigInteger)result[0]).longValue());
	      String awardsCountStr = (String)result[1];
	      int awardsCount = Integer.parseInt(awardsCountStr);
	      List<Awards> employeeAwards = this.repo.findByEmployeeId(employeeId);
	      for (Awards awards : employeeAwards) {
	        Map<String, Object> awardsMap = new HashMap<>();
	        awardsMap.put("awardsId", Long.valueOf(awards.getAwardsId()));
	        awardsMap.put("description", awards.getDescription());
	        awardsMap.put("date", awards.getDate());
	        awardsMap.put("employeeId", Long.valueOf(awards.getEmployeeId()));
	        awardsMap.put("status", Boolean.valueOf(awards.isStatus()));
	        List<Map<String, Object>> awardsPhotosList = new ArrayList<>();
	        for (AwardsPhoto awardsPhoto : awards.getAwardsPhotos()) {
	          Map<String, Object> awardsPhotoMap = new HashMap<>();
	          awardsPhotoMap.put("awardsPhotoId", Long.valueOf(awardsPhoto.getAwardsPhotoId()));
	          awardsPhotoMap.put("url", "/image/" + 
	              generateRandomNumber() + "/" + awardsPhoto.getAwardsPhotoId());
	          awardsPhotosList.add(awardsPhotoMap);
	        } 
	        awardsMap.put("awardsPhotos", awardsPhotosList);
	        awardsMap.put("awardsCount", Integer.valueOf(awardsCount));
	        employeeAwardsList.add(awardsMap);
	      } 
	    } 
	    return employeeAwardsList;
	  }
	}


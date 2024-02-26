package com.example.HRM.entity.accounting;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "server")
public class Server {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serverId;

	private String serverName;

	private Date date;

	private boolean Status;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_serverId", referencedColumnName = "serverId")
	private List<ServerList> server;

	public Long getServerId() {
		return this.serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ServerList> getServer() {
		return this.server;
	}

	public void setServer(List<ServerList> server) {
		this.server = server;
	}

	public boolean isStatus() {
		return this.Status;
	}

	public void setStatus(boolean status) {
		this.Status = status;
	}
}

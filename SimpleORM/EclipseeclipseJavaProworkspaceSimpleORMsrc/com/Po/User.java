package com.Po;
import java.sql.*;
import java.util.*;

public class User {

	private null Alter_priv;
	private null References_priv;
	private null Process_priv;
	private java.sql.Blob ssl_cipher;
	private null password_lifetime;
	private null File_priv;
	private null Drop_priv;
	private null Password_require_current;
	private null Create_priv;
	private null Show_view_priv;
	private java.sql.Blob x509_issuer;
	private null Insert_priv;
	private null Create_tmp_table_priv;
	private null Create_view_priv;
	private Integer id;
	private java.sql.Timestamp password_last_changed;
	private java.sql.Timestamp regtime;
	private null Shutdown_priv;
	private null password_expired;
	private null Event_priv;
	private null Delete_priv;
	private java.sql.Blob x509_subject;
	private null max_updates;
	private null Drop_role_priv;
	private Integer pwd;
	private null Create_routine_priv;
	private String User;
	private null Index_priv;
	private null account_locked;
	private null Repl_slave_priv;
	private null Trigger_priv;
	private null Password_reuse_time;
	private null Select_priv;
	private null authentication_string;
	private java.sql.Date latestOnline;
	private null Create_tablespace_priv;
	private null Execute_priv;
	private null Alter_routine_priv;
	private null Repl_client_priv;
	private null Lock_tables_priv;
	private null ssl_type;
	private null max_questions;
	private null max_connections;
	private String Host;
	private null Grant_priv;
	private null Password_reuse_history;
	private null max_user_connections;
	private null Super_priv;
	private String plugin;
	private null Reload_priv;
	private null Update_priv;
	private null Create_user_priv;
	private null Create_role_priv;
	private null User_attributes;
	private null Show_db_priv;
	private String username;


	public null getAlter_priv() {
		return Alter_priv;
	}
	public null getReferences_priv() {
		return References_priv;
	}
	public null getProcess_priv() {
		return Process_priv;
	}
	public java.sql.Blob getSsl_cipher() {
		return ssl_cipher;
	}
	public null getPassword_lifetime() {
		return password_lifetime;
	}
	public null getFile_priv() {
		return File_priv;
	}
	public null getDrop_priv() {
		return Drop_priv;
	}
	public null getPassword_require_current() {
		return Password_require_current;
	}
	public null getCreate_priv() {
		return Create_priv;
	}
	public null getShow_view_priv() {
		return Show_view_priv;
	}
	public java.sql.Blob getX509_issuer() {
		return x509_issuer;
	}
	public null getInsert_priv() {
		return Insert_priv;
	}
	public null getCreate_tmp_table_priv() {
		return Create_tmp_table_priv;
	}
	public null getCreate_view_priv() {
		return Create_view_priv;
	}
	public Integer getId() {
		return id;
	}
	public java.sql.Timestamp getPassword_last_changed() {
		return password_last_changed;
	}
	public java.sql.Timestamp getRegtime() {
		return regtime;
	}
	public null getShutdown_priv() {
		return Shutdown_priv;
	}
	public null getPassword_expired() {
		return password_expired;
	}
	public null getEvent_priv() {
		return Event_priv;
	}
	public null getDelete_priv() {
		return Delete_priv;
	}
	public java.sql.Blob getX509_subject() {
		return x509_subject;
	}
	public null getMax_updates() {
		return max_updates;
	}
	public null getDrop_role_priv() {
		return Drop_role_priv;
	}
	public Integer getPwd() {
		return pwd;
	}
	public null getCreate_routine_priv() {
		return Create_routine_priv;
	}
	public String getUser() {
		return User;
	}
	public null getIndex_priv() {
		return Index_priv;
	}
	public null getAccount_locked() {
		return account_locked;
	}
	public null getRepl_slave_priv() {
		return Repl_slave_priv;
	}
	public null getTrigger_priv() {
		return Trigger_priv;
	}
	public null getPassword_reuse_time() {
		return Password_reuse_time;
	}
	public null getSelect_priv() {
		return Select_priv;
	}
	public null getAuthentication_string() {
		return authentication_string;
	}
	public java.sql.Date getLatestOnline() {
		return latestOnline;
	}
	public null getCreate_tablespace_priv() {
		return Create_tablespace_priv;
	}
	public null getExecute_priv() {
		return Execute_priv;
	}
	public null getAlter_routine_priv() {
		return Alter_routine_priv;
	}
	public null getRepl_client_priv() {
		return Repl_client_priv;
	}
	public null getLock_tables_priv() {
		return Lock_tables_priv;
	}
	public null getSsl_type() {
		return ssl_type;
	}
	public null getMax_questions() {
		return max_questions;
	}
	public null getMax_connections() {
		return max_connections;
	}
	public String getHost() {
		return Host;
	}
	public null getGrant_priv() {
		return Grant_priv;
	}
	public null getPassword_reuse_history() {
		return Password_reuse_history;
	}
	public null getMax_user_connections() {
		return max_user_connections;
	}
	public null getSuper_priv() {
		return Super_priv;
	}
	public String getPlugin() {
		return plugin;
	}
	public null getReload_priv() {
		return Reload_priv;
	}
	public null getUpdate_priv() {
		return Update_priv;
	}
	public null getCreate_user_priv() {
		return Create_user_priv;
	}
	public null getCreate_role_priv() {
		return Create_role_priv;
	}
	public null getUser_attributes() {
		return User_attributes;
	}
	public null getShow_db_priv() {
		return Show_db_priv;
	}
	public String getUsername() {
		return username;
	}
	public void setAlter_priv(null Alter_priv) {
		this.Alter_priv = Alter_priv;
	}
	public void setReferences_priv(null References_priv) {
		this.References_priv = References_priv;
	}
	public void setProcess_priv(null Process_priv) {
		this.Process_priv = Process_priv;
	}
	public void setSsl_cipher(java.sql.Blob ssl_cipher) {
		this.ssl_cipher = ssl_cipher;
	}
	public void setPassword_lifetime(null password_lifetime) {
		this.password_lifetime = password_lifetime;
	}
	public void setFile_priv(null File_priv) {
		this.File_priv = File_priv;
	}
	public void setDrop_priv(null Drop_priv) {
		this.Drop_priv = Drop_priv;
	}
	public void setPassword_require_current(null Password_require_current) {
		this.Password_require_current = Password_require_current;
	}
	public void setCreate_priv(null Create_priv) {
		this.Create_priv = Create_priv;
	}
	public void setShow_view_priv(null Show_view_priv) {
		this.Show_view_priv = Show_view_priv;
	}
	public void setX509_issuer(java.sql.Blob x509_issuer) {
		this.x509_issuer = x509_issuer;
	}
	public void setInsert_priv(null Insert_priv) {
		this.Insert_priv = Insert_priv;
	}
	public void setCreate_tmp_table_priv(null Create_tmp_table_priv) {
		this.Create_tmp_table_priv = Create_tmp_table_priv;
	}
	public void setCreate_view_priv(null Create_view_priv) {
		this.Create_view_priv = Create_view_priv;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPassword_last_changed(java.sql.Timestamp password_last_changed) {
		this.password_last_changed = password_last_changed;
	}
	public void setRegtime(java.sql.Timestamp regtime) {
		this.regtime = regtime;
	}
	public void setShutdown_priv(null Shutdown_priv) {
		this.Shutdown_priv = Shutdown_priv;
	}
	public void setPassword_expired(null password_expired) {
		this.password_expired = password_expired;
	}
	public void setEvent_priv(null Event_priv) {
		this.Event_priv = Event_priv;
	}
	public void setDelete_priv(null Delete_priv) {
		this.Delete_priv = Delete_priv;
	}
	public void setX509_subject(java.sql.Blob x509_subject) {
		this.x509_subject = x509_subject;
	}
	public void setMax_updates(null max_updates) {
		this.max_updates = max_updates;
	}
	public void setDrop_role_priv(null Drop_role_priv) {
		this.Drop_role_priv = Drop_role_priv;
	}
	public void setPwd(Integer pwd) {
		this.pwd = pwd;
	}
	public void setCreate_routine_priv(null Create_routine_priv) {
		this.Create_routine_priv = Create_routine_priv;
	}
	public void setUser(String User) {
		this.User = User;
	}
	public void setIndex_priv(null Index_priv) {
		this.Index_priv = Index_priv;
	}
	public void setAccount_locked(null account_locked) {
		this.account_locked = account_locked;
	}
	public void setRepl_slave_priv(null Repl_slave_priv) {
		this.Repl_slave_priv = Repl_slave_priv;
	}
	public void setTrigger_priv(null Trigger_priv) {
		this.Trigger_priv = Trigger_priv;
	}
	public void setPassword_reuse_time(null Password_reuse_time) {
		this.Password_reuse_time = Password_reuse_time;
	}
	public void setSelect_priv(null Select_priv) {
		this.Select_priv = Select_priv;
	}
	public void setAuthentication_string(null authentication_string) {
		this.authentication_string = authentication_string;
	}
	public void setLatestOnline(java.sql.Date latestOnline) {
		this.latestOnline = latestOnline;
	}
	public void setCreate_tablespace_priv(null Create_tablespace_priv) {
		this.Create_tablespace_priv = Create_tablespace_priv;
	}
	public void setExecute_priv(null Execute_priv) {
		this.Execute_priv = Execute_priv;
	}
	public void setAlter_routine_priv(null Alter_routine_priv) {
		this.Alter_routine_priv = Alter_routine_priv;
	}
	public void setRepl_client_priv(null Repl_client_priv) {
		this.Repl_client_priv = Repl_client_priv;
	}
	public void setLock_tables_priv(null Lock_tables_priv) {
		this.Lock_tables_priv = Lock_tables_priv;
	}
	public void setSsl_type(null ssl_type) {
		this.ssl_type = ssl_type;
	}
	public void setMax_questions(null max_questions) {
		this.max_questions = max_questions;
	}
	public void setMax_connections(null max_connections) {
		this.max_connections = max_connections;
	}
	public void setHost(String Host) {
		this.Host = Host;
	}
	public void setGrant_priv(null Grant_priv) {
		this.Grant_priv = Grant_priv;
	}
	public void setPassword_reuse_history(null Password_reuse_history) {
		this.Password_reuse_history = Password_reuse_history;
	}
	public void setMax_user_connections(null max_user_connections) {
		this.max_user_connections = max_user_connections;
	}
	public void setSuper_priv(null Super_priv) {
		this.Super_priv = Super_priv;
	}
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}
	public void setReload_priv(null Reload_priv) {
		this.Reload_priv = Reload_priv;
	}
	public void setUpdate_priv(null Update_priv) {
		this.Update_priv = Update_priv;
	}
	public void setCreate_user_priv(null Create_user_priv) {
		this.Create_user_priv = Create_user_priv;
	}
	public void setCreate_role_priv(null Create_role_priv) {
		this.Create_role_priv = Create_role_priv;
	}
	public void setUser_attributes(null User_attributes) {
		this.User_attributes = User_attributes;
	}
	public void setShow_db_priv(null Show_db_priv) {
		this.Show_db_priv = Show_db_priv;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User() {}
}

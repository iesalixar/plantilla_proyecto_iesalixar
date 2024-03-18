package com.fitconnet.dto.response.user;

public class UsuarioResponse {
	 private String firstName;
	    private String lastName;
	    private String email;
	    private String rol;
	    

		public UsuarioResponse(String firstName, String lastName, String email, String rol) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.rol = rol;
		}

		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRol() {
			return rol;
		}
		public void setRol(String rol) {
			this.rol = rol;
		}
		
	    
	    
}

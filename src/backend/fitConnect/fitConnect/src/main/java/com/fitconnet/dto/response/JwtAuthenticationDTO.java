package com.fitconnet.dto.response;


public class JwtAuthenticationDTO {
    private String token;

	public JwtAuthenticationDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    public static class JwtAuthenticationResponseBuilder {
        private String token;

        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public JwtAuthenticationDTO build() {
            return new JwtAuthenticationDTO(token);
        }
    }
    
}

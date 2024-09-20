public class Usuario {

	private int id;
    private String username;
    private String password;
    
    public Usuario() {
	}
    
    public Usuario(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id= ").append(id).append(", username= ").append(username).append(", password= ********").append("]");
		return builder.toString();
	}
    
    
}

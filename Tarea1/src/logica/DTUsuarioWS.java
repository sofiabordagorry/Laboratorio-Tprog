package logica;

public class DTUsuarioWS {
	private DTUsuario[] users;

	public DTUsuarioWS(){	
	}
	
	public DTUsuarioWS(DTUsuario[] users) {
		this.users = users;
	}
	
	public DTUsuario[] getUsers() {
		return users;
	}

	public void setUsers(DTUsuario[] users) {
		this.users = users;
	}
}

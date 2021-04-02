
public class MainSetup {
	
	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public MainSetup(String filepath) {
		super();
		this.filepath = filepath;
	}
	
	
	public MainSetup() {
		
		this.filepath = "C:/Users/jicro/Desktop/Games";
		//default filepath;
	}
	

}

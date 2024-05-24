package api.endpoints;

public class Routes {

	
	public static String base_url="https://userserviceapi-a54ceee3346a.herokuapp.com/uap";
	public static String get_all_users_url=base_url+"/users";
	public static String get_url_id=base_url+"/user/{userId}";
	public static String get_url_firstname=base_url+"/users/username/{userFirstName}";
	public static String post_url=base_url+"/createusers";
	public static String update_url=base_url+"/updateuser/{userId}";
	public static String delete_url_id=base_url+"/deleteuser/{userId}";
	public static String delete_url_firstname=base_url+"/deleteuser/username/{userFirstName}";
	
	
}

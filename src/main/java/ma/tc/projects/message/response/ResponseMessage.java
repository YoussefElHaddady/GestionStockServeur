package ma.tc.projects.message.response;


/**
 * Role : ResponseMessage object is just a message object.
 * 
 *
 */
public class ResponseMessage {
  private String message;
  
 
  public ResponseMessage(String message) {
    this.message = message;
  }
 
  public String getMessage() {
    return message;
  }
 
  public void setMessage(String message) {
    this.message = message;
  }
  
  
  
  public static final String INSERT_SUCCESS = "insert successfully!";
  public static final String INSERT_FAIL = "insert failed!";
  
  public static final String UPDATE_SUCCESS = "update successfully!";
  public static final String UPDATE_FAIL = "update failed!";
  
  public static final String DELETE_SUCCESS = "delete successfully!";
  public static final String DELETE_FAIL = "delete failed!";
  
  public static final String NOT_IMPLEMENTED_FEATURE = "this feature not implemented yet.";
}
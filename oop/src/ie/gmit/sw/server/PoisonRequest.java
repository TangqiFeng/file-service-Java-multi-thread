package ie.gmit.sw.server;
/**
 * 
 * This class do nothing, just extends Request, it's used to 
 * interrupt the blockingQueue if needed, cause blocking 
 * queue will not know when to stop waiting...
 *
 */
public class PoisonRequest extends Request {
	String flag = "POISON";
	
	
}

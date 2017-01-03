package ie.gmit.sw.client;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Parseator {
	private Context ctx;

	public Parseator(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	public void init() throws Throwable{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CON_FILE);
		
		//get the root of the node tree
		Element root = doc.getDocumentElement();
		NodeList children = root.getChildNodes();
		//find the elements
		NamedNodeMap atts = root.getAttributes();
		for(int j=0;j<atts.getLength();j++){
			if(atts.item(j).getNodeName().equals("username")){
				ctx.setUsername(atts.item(0).getNodeValue());
			}
		}
		
		for(int i=0;i<children.getLength();i++){
			Node next = children.item(i);
			
			if(next instanceof Element){
				Element e = (Element)next;
				
				if(e.getNodeName().equals("server-host")){
					ctx.setServer_host(e.getFirstChild().getNodeName());
				}else if(e.getNodeName().equals("server-port")){
					ctx.setServer_port(e.getFirstChild().getNodeName());
				}else if(e.getNodeName().equals("download-dir")){
					ctx.setDownload_dir(e.getFirstChild().getNodeName());
				}
			}	
		}
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
	
	

}

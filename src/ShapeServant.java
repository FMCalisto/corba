import org.omg.CosNaming.*;
import org.omg.CORBA.*;


class ShapeServant extends ShapePOA {
    int myVersion;
    GraphicalObject myG;
    
    public ShapeServant(GraphicalObject g, int version){
 		myG = g;
        myVersion = version;
    }
    
     
	public int getVersion(){
	    return myVersion;
	}
	
   
   public GraphicalObject getAllState() {
        return myG;
   }
   
    
}


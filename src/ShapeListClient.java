//
// Tester.java   
//
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Properties;


import java.util.Vector;

public class ShapeListClient {

	public static void main(String args[]) {
		String option = "Read";
		String shapeType = "Rectangle";
		if(args.length > 0)  option = args[0];	// read or write
		if(args.length > 1)  shapeType = args[1];	// specify Circle, Line etc
        try{
            // create and initialize the ORB at port number 1500
            Properties props = new Properties();
        	props.put("org.omg.CORBA.ORBInitialPort", "1500");
        	ORB orb = ORB.init(args, props);
               
			// get the root naming context
			try{
				org.omg.CORBA.Object objRef = 
					orb.resolve_initial_references("NameService");
            	NamingContext ncRef = NamingContextHelper.narrow(objRef);
          	// resolve the Object Reference in Naming
            	NameComponent nc = new NameComponent("ShapeList", "");
       			NameComponent path [] = { nc };
       			  
        		try{
           			ShapeList shapeListRef = ShapeListHelper.narrow(ncRef.resolve(path));
            		if(option.equals("Read")){
            				Shape[] sList = shapeListRef.allShapes();
       					for(int i=0; i<sList.length; i++){
     						if (sList[i]== null) break;
       					GraphicalObject g = sList[i].getAllState();
        					// g.print();
        				}
           			} else {
           				System.out.println("Shape type = " + shapeType);
                		GraphicalObject g = new GraphicalObject(shapeType, new Rectangle(50,50,300,400), false);
          				try {
          					shapeListRef.newShape(g);
          					System.out.println("Added a shape");
          				}catch(ShapeListPackage.FullException e) {}
          			}
        		}catch(Exception e){System.out.println("ERROR : " + e);}
  			}catch(org.omg.CORBA.ORBPackage.InvalidName e){}
       } catch (org.omg.CORBA.SystemException e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
}

		
	

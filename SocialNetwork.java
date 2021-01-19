import java.util.*;
import java.text.SimpleDateFormat;

class Point2D{
    double x,y;
    public Point2D(double x,double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
}

class SocialNetwork{ //SocialNetwork
    static Set<Node> set_of_nodes = new HashSet<Node>();
    
    static Node createNode(Scanner scan){
        System.out.println("\n====NODE CREATION====");
        System.out.println("-:Select the type of node you want to create:-");
        System.out.println("- Enter (1) to create an INDIVIDUAL node.\n- Enter (2) to create a BUSINESS node.\n- Enter (3) to create a GROUP node.\n- Enter (4) to create an ORGANISATION node.\n");
		System.out.print("Your Response: ");
		String type = scan.nextLine();
		System.out.println("\nInitializing...\n");
		
        if(type.equals("1")) return new Individual(scan);
        if(type.equals("2")) return new Business(scan);
        if(type.equals("3")) return new Group(scan);
        if(type.equals("4")) return new Organisation(scan);
        else{
            System.out.println("Invalid Response");
            return null;
        }
    }
    
    static void deleteNode(Scanner scan){
        System.out.println("\n====NODE DELETION====");
        System.out.print("Enter the node ID which you want to delete: ");
        String ID = scan.nextLine();
        int id = Integer.parseInt(ID);
        int flag = 0;
        for(Node n:set_of_nodes){
            if(id==n.id){
                set_of_nodes.remove(n);
                n = null;
                flag = 1;
                break;
            }
        }
        if(flag==1) System.out.println("DELETION SUCCESSFUL");
        else System.out.println("DELETION UNSUCCESSFUL");
    }
    
    static Set<Node> searchNode(Scanner scan){
        System.out.println("\n====NODE SEARCH====");
        Set<Node> searchResults = new HashSet<Node>();
        System.out.println("-:Select the attribute by which you want to search:- ");
        System.out.println("- Enter (1) for name.\n- Enter (2) for type.\n- Enter (3) for birthday.");
        System.out.print("\nYour response: ");
		String attribute = scan.nextLine();
		if(attribute.equals("1")){
		    System.out.print("Enter the name: ");
		    String name = scan.nextLine();
		    for(Node n:set_of_nodes){
		        if(name.equals(n.name)){
		            searchResults.add(n);
		        }
		    }
		}
		else if(attribute.equals("2")){
		    System.out.print("Enter the type: ");
		    String type = scan.nextLine();
		    for(Node n:set_of_nodes){
		        if(type.equals(n.type)){
		            searchResults.add(n);
		        }
		    }
		}
		else if(attribute.equals("3")){
		    System.out.print("Enter the birthday: ");
		    String birthday = scan.nextLine();
            for(Node n:set_of_nodes){
                if(birthday.equals(n.birthday)){
                    searchResults.add(n);
                }
            }
		}
		else{
		    System.out.println("\nInvalid response");
		}
		return searchResults;
    }
    
    public static void main(String[] args) {
        System.out.println("!!--++ Welcome to the Social Network ++--!!\n");
		Scanner scan = new Scanner(System.in);
		
		menu();
		String response = scan.nextLine();
        
		while(!response.equals("9")){
		    try{
    		    if(response.equals("1")){
    		        Node n = createNode(scan);
    		        if(n!=null) System.out.println("\nCREATION SUCCESSFUL");
    		        else System.out.println("CREATION UNSUCCESSFUL");
    		    }
    		    else if(response.equals("2")){
    		        deleteNode(scan);
    		    }
    		    else if(response.equals("3")){
    		        Set<Node> sr = searchNode(scan);
    		        System.out.println("Searching...");
    		        if(!sr.isEmpty()){
    		            System.out.println("SEARCH RESULT:-");
        		        for(Node n:sr){
        	                n.printObj();
        	            }
        		    }
        		    else System.out.println("\nNO RESULTS FOUND");
    		    }
    		    else if(response.equals("4")){
    		        System.out.println("\n====PRINTING ALL LINKED NODES====");
    		        System.out.print("Enter the user ID: ");
    		        String ID = scan.nextLine();
    		        int id = Integer.parseInt(ID);
    		        int flag = 0;
    		        for(Node n:set_of_nodes){
    		            if(id==n.id){
    		                flag = 1;
    		                System.out.println("The users linked are:-");
    		                for(Node s:n.set_of_links){
    		                    if(set_of_nodes.contains(s)){
        		                    flag = 2;
        		                    //System.out.println("- "+s.name);
        		                    s.printObj();
    		                    }
    		                }
    		                if(flag!=2) System.out.println("There are no users linked");
    		            }
    		        }
    		        if(flag==0) System.out.println("\nUSER NOT FOUND");
    		    }
    		    else if(response.equals("5")){
    		        System.out.println("\n====CREATE AND POST CONTENT====");
    		        System.out.print("Enter the user ID: ");
    		        String ID = scan.nextLine();
    		        int id = Integer.parseInt(ID);
    		        int flag = 0;
    		        for(Node n:set_of_nodes){
    		            if(id==n.id){
    		                flag = 1;
    		                n.createContent(scan);
    		                break;
    		            }
    		        }
    		        if(flag==0) System.out.println("\nUSER NOT FOUND");
    		    }
    		    else if(response.equals("6")){
    		        System.out.println("\n====SEARCH FOR CONTENT====");
    		        System.out.print("Enter the required user ID: ");
    		        String ID = scan.nextLine();
    		        int id = Integer.parseInt(ID);
    		        int flag = 0;
    		        for(Node n:set_of_nodes){
    		            if(id==n.id){
    		                flag =1;
    		                int i = 1;
    		                for(String s:n.set_of_postedContent){
    		                    flag = 2;
    		                    System.out.println("CONTENT "+ i+": "+s+"\n");
    		                    i = i+1;
    		                }
    		                if(flag!=2) System.out.println("*No content available*");
    		                break;
    		            }
    		        }
    		        if(flag==0) System.out.println("\nUSER NOT FOUND");
    		    }
    		    else if(response.equals("7")){
    		        System.out.println("\n====DISPLAY ALL CONTENT LINKED TO AN USER====");
    		        System.out.print("Enter the user ID: ");
    		        String ID = scan.nextLine();
    		        int id = Integer.parseInt(ID);
    		        int flag = 0;
    		        for(Node n:set_of_nodes){
    		            if(id==n.id){
    		                flag = 1;
    		                for(Node s:n.set_of_links){
    		                    int f = 0; 
    		                    if(set_of_nodes.contains(s)){
                		            System.out.println("Posted Contents of <"+s.name+">");
                		            int i=1;
                		      
                		            for(String content:s.set_of_postedContent){
                		                f = 1;
                		                System.out.print("CONTENT "+ i+": "+content+"\n");
                		                i = i+1;
                		            }
    		                    }
            		            if(f==0) System.out.println("*No content available*");
            		            System.out.print("\n");
    		                }
    		            }
    		        }
    		        if(flag==0) System.out.println("\nUSER NOT FOUND");
    		    }
    		    else if(response.equals("8")){
    		        System.out.println("\n====PRINTING ALL NODES====");
    		        System.out.println("---------------------------------------------------");
    		        for(Node n:set_of_nodes){
    		            n.printObj();       
    		            System.out.println("---------------------------------------------------");
    		        }
    		    }
    		    else if(response.equals("9")){
    		        break;
    		    }
    		    else {
    		        System.out.println("RESPONSE NOT APPLICABLE!\nPLEASE TRY AGAIN");
    		    }
    		    menu();
    		    response = scan.nextLine();
		    }
		    catch(NumberFormatException E){
    		    System.out.println("\nWRONG INPUT!!\nPLEASE TRY AGAIN");
		    }
		}
		
		System.out.println("\nTHANK YOU!!\nDO VISIT AGAIN!!\n");
	}
	
	public static void menu(){
	    System.out.println("\n===============:Operations Available:===============");
	    System.out.println("- Enter (1) to CREATE a new user/node.\n- Enter (2) to DELETE an existing user/node.\n- Enter (3) to SEARCH for an existing user/node.");
	    System.out.println("- Enter (4) to PRINT all linked users/nodes to a given input user/node.\n- Enter (5) to CREATE and POST content by a user.");
	    System.out.println("- Enter (6) to SEARCH for content posted by any user/node.\n- Enter (7) to DISPLAY all content posted by users/nodes linked to a given user/node.");
	    System.out.println("- Enter (8) to DISPLAY all users/nodes in the system.\n- Enter (9) to QUIT.\n");
	    System.out.print("Your Response: ");
	}
}

class Node extends SocialNetwork{
	int id;
	static int counter;

	String name;
	String type;
	String birthday = null;
	Set<Node> set_of_links;
	Date creation_date;
	Set<String> set_of_postedContent;

	public Node(Scanner scan) {
	    set_of_nodes.add(this);
	    
	    counter++;
	    this.id = counter;
	    
		this.creation_date = new Date();
		
		System.out.print("Enter the name: ");
		this.name = scan.nextLine();
		
		this.set_of_links = new HashSet<Node>();
		
		this.set_of_postedContent = new HashSet<String>();
		//createContent(scan);
	}
	
	public void createContent(Scanner scan){
	    //System.out.print("Want to create content? (Y/N): ");
		String reply = "Y";
		while(!reply.equals("N")){
		    System.out.println("Enter the content: ");
		    String content = scan.nextLine();
		    System.out.print("Are you sure you want to post? (Y/N): ");
		    String sure = scan.nextLine();
		    if(sure.equals("Y")){
		        this.set_of_postedContent.add(content);
		        System.out.println("\n++ Content Posted ++\n");
		    }
		    else{
		        System.out.println("\n-- Content Discarded --\n");
		    }
		    System.out.print("Want to create any more content? (Y/N): ");
		    reply = scan.nextLine();
		}
	}
	
	public void printObj(){
	    System.out.println("===================================================");
	    System.out.println("User ID: "+this.id);
	    System.out.println("Creation Date: "+this.creation_date);
	    System.out.println("User Type: "+this.type);
	    System.out.println("Name: "+this.name);
	}
	
	public Node searchNodeByID(int ID){
	    for(Node n:set_of_nodes){
	        if(ID==n.id){
	            return n;
	        }
	    }
	    return null;
	}
	
}

class Individual extends Node{
    public Individual(Scanner scan){
        super(scan);
        this.type = "Individual";
        addBirthday(scan);
    }
    public void addBirthday(Scanner scan){
        System.out.print("Want to enter birthday? (Y/N): ");
        String reply = scan.nextLine();
        if(reply.equals("Y")){
            System.out.print("Enter the birthday (dd-mm-yyyy): ");
            this.birthday = scan.nextLine();
        }
        else this.birthday = null;
    }
    public void printObj(){
        super.printObj();
        if(this.birthday!=null) System.out.println("Birthday: "+ this.birthday);
        System.out.println("===================================================");
    }
}

class Business extends Node{
    Point2D location;
    Set<Node> Owners,Customers;
    
    public Business(Scanner scan){
        super(scan);
        this.type = "Business";
        System.out.println("Enter the location (x & y)");
        double x,y;
        System.out.print("Enter x coordinate: ");
        x = scan.nextDouble();
        System.out.print("Enter y coordinate: ");
        y = scan.nextDouble();
         
        location = new Point2D(x,y);
        this.Owners = new HashSet<Node>();
        this.Customers = new HashSet<Node>();
        addOwners(scan);
        addCustomers(scan);
    }
    
    public void addOwners(Scanner scan){
        String buffer = scan.nextLine();
        System.out.print("\nWant to add owners? (Y/N): ");
        //System.out.println(reply);
        String reply = scan.nextLine();
        //System.out.println(reply);
        while(!reply.equals("N")){
            System.out.print("Enter owner's ID: ");
            String ID = scan.nextLine();
		    int id = Integer.parseInt(ID);
            Node n = searchNodeByID(id);
            if(n!=null && n.type=="Individual"){
                this.Owners.add(n);
                this.set_of_links.add(n);
                n.set_of_links.add(this);
                System.out.println(n.name+" is now an owner of "+this.name+".\n");
            }
            else{
                System.out.println(n.name+" is NOT APPLICABLE\n");
            }
            System.out.print("Want to add more owners? (Y/N): ");
            reply = scan.nextLine();
        }
    }
    
    public void addCustomers(Scanner scan){
        System.out.print("\nWant to add customers ? (Y/N): ");
        String reply = scan.nextLine();
        while(!reply.equals("N")){
            System.out.print("Enter customer's ID: ");
            String ID = scan.nextLine();
		    int id = Integer.parseInt(ID);
            Node n = searchNodeByID(id);
            if(n!=null && n.type=="Individual"){
                this.Customers.add(n);
                this.set_of_links.add(n);
                n.set_of_links.add(this);
                System.out.println(n.name+" is now a customer of "+this.name+"\n");
            }
            else{
                System.out.println(n.name+" is NOT APPLICABLE\n");
            }
            System.out.print("Want to add more customers? (Y/N): ");
            reply = scan.nextLine();
        }
    }
    public void printObj(){
        super.printObj();
        System.out.println("Location of Business: ("+this.location.getX()+","+this.location.getY()+")");
	    System.out.println("===================================================");
    }
}

class Organisation extends Node{
    Point2D location;
    Set<Node> Members;
    
    public Organisation(Scanner scan){
        super(scan);
        this.type = "Organisation";
        System.out.println("Enter the location (x & y)");
        double x,y;
        System.out.print("Enter x coordinate: ");
        x = scan.nextDouble();
        System.out.print("Enter y coordinate: ");
        y = scan.nextDouble();
         
        location = new Point2D(x,y);
        this.Members = new HashSet<Node>();
        addMembers(scan);
    }
    
    public void addMembers(Scanner scan){
        String buffer = scan.nextLine();
        System.out.print("\nWant to add Members ? (Y/N): ");
        String reply = scan.nextLine();
        while(!reply.equals("N")){
            System.out.print("Enter Member's ID: ");
            String ID = scan.nextLine();
		    int id = Integer.parseInt(ID);
        
            Node n = searchNodeByID(id);
            if(n!=null && n.type=="Individual"){
                this.Members.add(n);
                this.set_of_links.add(n);
                n.set_of_links.add(this);
                System.out.println(n.name+ " is successfully added to "+this.name+"\n");
            }
            else{
                System.out.println(n.name+" is NOT APPLICABLE");
            }
            System.out.print("Want to add more members? (Y/N): ");
            reply = scan.nextLine();
        }
    }
    
    public void printObj(){
        super.printObj();
        System.out.println("Location of Organisation: ("+this.location.getX()+","+this.location.getY()+")");
	    System.out.println("===================================================");
    }
}

class Group extends Node{
    Set<Node> Members;
    
    public Group(Scanner scan){
        super(scan);
        this.type = "Group";
        this.Members = new HashSet<Node>();
        addMembers(scan);
    }
    
    public void addMembers(Scanner scan){
        //String buffer = scan.nextLine();
        System.out.print("\nWant to add Members ? (Y/N): ");
        String reply = scan.nextLine();
        while(!reply.equals("N")){
            System.out.print("Enter Member's ID: ");
            String ID = scan.nextLine();
		    int id = Integer.parseInt(ID);
            Node n = searchNodeByID(id);
            if(n!=null && (n.type.equals("Individual") || n.type.equals("Business"))){
                this.Members.add(n);
                this.set_of_links.add(n);
                n.set_of_links.add(this);
                System.out.println(n.name+ " is successfully added to "+this.name+"\n");
            }
            else{
                System.out.println(n.name+" is NOT APPLICABLE");
            }
            System.out.print("Want to add more members? (Y/N): ");
            reply = scan.nextLine();
        }
    }
    public void printObj(){
        super.printObj();
	    System.out.println("===================================================");
    }
}
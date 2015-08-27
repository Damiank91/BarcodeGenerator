/**
 * UPC-A
 * www.algorytm.org
 * (c)2007 Tomasz Lubinski
 */

public class UPC_A {

	private static UPC_A_Validator UPCAVal;

	public static void main(String[] args) {
		
		String UPC_A;
		
        System.out.println("Podaj numer UPC-A");
        UPC_A = Console.readString("?");
        UPC_A = UPC_A.replaceAll("-", "");  //remove all -
        UPC_A = UPC_A.replaceAll(" ", "");  //remove all spaces      
        UPCAVal = new UPC_A_Validator(UPC_A);
        
        if (UPCAVal.isValid()) {
        	System.out.println("Numer UPC-A jest prawid³owy");
        	System.out.println("System: " + UPCAVal.getSystem());
        	
        }
        else {
        	System.out.println("Numer UPC-A jest nieprawid³owy");
        }
	}
}

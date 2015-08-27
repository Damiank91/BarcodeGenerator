/**
 * @author Tomasz Lubinski
 * www.algorym.org
 * (c) 2007
 *
 * Class for UPC-A validating
 */

public class UPC_A_Validator {

	private byte UPC_A[] = new byte[12];
	private boolean valid = false;
	private String Systems[] =  {
	        "zwyk³y kod UPC", "zarezerwowany", "waga prduktów wa¿onych w sklepie",
	        "lekarstwa", "artyku³ nie bêd¹cy ¿ywnoœci¹", "kupony", "zarezerwowany",
	        "zwyk³y kod UPC", "zarezerwowany", "zarezerwowany" };
	
	public boolean isValid() {
		return valid;
	}
	
	public UPC_A_Validator(String UPCANumber) {
		if (UPCANumber.length() != 12) {
			valid = false;
		}
		else {
			for (int i = 0; i < 12; i++){
				UPC_A[i] = Byte.parseByte(UPCANumber.substring(i, i+1));
			}
			if (checkSum()) {
				valid = true;
			}
			else {
				valid = false;	
			}
		}
	}
	
	private boolean checkSum() {
		int sum = 3 * UPC_A[0] +
				  1 * UPC_A[1] +
				  3 * UPC_A[2] +
				  1 * UPC_A[3] +
				  3 * UPC_A[4] +
				  1 * UPC_A[5] +
				  3 * UPC_A[6] +
				  1 * UPC_A[7] +
				  3 * UPC_A[8] +
				  1 * UPC_A[9] +
				  3 * UPC_A[10];
		
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;
        
		if (sum == UPC_A[11]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getSystem() {
		return Systems[UPC_A[0]];
	}
	
}

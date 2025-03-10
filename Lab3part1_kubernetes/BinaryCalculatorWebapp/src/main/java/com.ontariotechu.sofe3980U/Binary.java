package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// uncomment the following code
		
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
  		
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

    //Bitwise OR method
    public static Binary bitwiseOr(Binary num1, Binary num2) {
        // the index of the first digit of each number
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        
        // Initialize a StringBuilder to build the result binary number
        StringBuilder result = new StringBuilder();
        
        // Loop through both binary numbers from right to left (least significant bit to most significant)
        while (ind1 >= 0 || ind2 >= 0) {
            int bit1 = (ind1 >= 0) ? (num1.number.charAt(ind1) == '1' ? 1 : 0) : 0; // Get the bit from num1 or 0 if out of range
            int bit2 = (ind2 >= 0) ? (num2.number.charAt(ind2) == '1' ? 1 : 0) : 0; // Get the bit from num2 or 0 if out of range
            
            // Perform the bitwise OR: 1 if either bit1 or bit2 is 1
            int orResult = (bit1 | bit2);
            
            // Append the result bit ('0' or '1') to the result
            result.insert(0, orResult == 1 ? "1" : "0");
            
            // Move to the next bit (left)
            ind1--;
            ind2--;
        }
        
        // Return the final binary result (leading zeros will naturally be handled)
        return new Binary(result.toString());
    }

	//Bitwise AND method
    public static Binary bitwiseAnd(Binary num1, Binary num2) {
        // the index of the first digit of each number
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        
        // Initialize a StringBuilder to build the result binary number
        StringBuilder result = new StringBuilder();
        
        // Loop through both binary numbers from right to left (least significant bit to most significant)
        while (ind1 >= 0 || ind2 >= 0) {
            int bit1 = (ind1 >= 0) ? (num1.number.charAt(ind1) == '1' ? 1 : 0) : 0; // Get the bit from num1 or 0 if out of range
            int bit2 = (ind2 >= 0) ? (num2.number.charAt(ind2) == '1' ? 1 : 0) : 0; // Get the bit from num2 or 0 if out of range
            
            // Perform the bitwise OR: 1 if either bit1 or bit2 is 1
            int andResult = (bit1 & bit2);
            
            // Append the result bit ('0' or '1') to the result
            result.insert(0, andResult == 1 ? "1" : "0");
            
            // Move to the next bit (left)
            ind1--;
            ind2--;
        }
        
        // Return the final binary result (leading zeros will naturally be handled)
        return new Binary(result.toString());
    }

	public static Binary multiply(Binary num1, Binary num2) {
		String binary1 = num1.number;
		String binary2 = num2.number;
		
		// Initialize the result as "0"
		String result = "0";
		
		// Start multiplying each bit of the second binary number
		for (int i = binary2.length() - 1; i >= 0; i--) {
			// If the current bit in binary2 is '1', add shifted binary1 to the result
			if (binary2.charAt(i) == '1') {
				// Append zeros equivalent to the position (shift left)
				String shifted = binary1 + "0".repeat(binary2.length() - 1 - i);
				// Add the shifted binary1 to the result
				result = add(new Binary(result), new Binary(shifted)).getValue();
			}
		}
		
		return new Binary(result);
	}


}	
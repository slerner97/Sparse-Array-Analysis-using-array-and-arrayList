import java.util.Scanner; 
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class AssignmentDos {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		boolean ValidData = true;
		int array_length = 0;
		double density_value = 0;
		
		while(ValidData == true) {
			
			System.out.println("Enter integer array length: ");
			array_length = reader.nextInt();
			if(array_length <= 0) {
				ValidData = true;
			}
			if(array_length > 0) {
				break;
			}
		}
		while(ValidData == true) {
			System.out.println("Enter array density value: ");
			density_value = reader.nextDouble();
			if(density_value < 0.0 || density_value > 1.0) {
				ValidData = true;
			}
			if(density_value >= 0.0 && density_value <= 1.0) {
				break;
			}
			
		}
		reader.close();
		int[] denseArray = createArray(array_length, density_value);
		// creates denseArray
		ArrayList<int[]> sparseArray = createSparseArray(array_length, density_value);
		// creates sparseArray
		
		ArrayList<int[]> Density_To_Sparse = DensityToSparse(denseArray);
		// changes dense array into sparse array
		int[] newDenseArray = NewDenseArray(array_length, sparseArray);
		// creates equivalent dense array
		
		maxDense(denseArray);
		maxSparse(sparseArray);
		
		
	}
		// PART 2
	public static int[] createArray(int length, double density) {
		long start = System.nanoTime();
		
		int[] array_1 = new int[length];
		
		Random num = new Random();
		for(int i = 0; i < array_1.length; i++) {
			
			double random_double = num.nextDouble();
			int random_int = num.nextInt(1000000) + 1;
			
			if(random_double <= density) {
				array_1[i] = random_int;
			}
			else {
				array_1[i] = 0;
			} // end else
			
		} // end for loop
		
		long end = System.nanoTime() - start;
		System.out.println("create dense length: " + length + " time: " + end/1000000.0);
		
		return array_1;	
		
		} // end createArray method
		// PART 3
	public static ArrayList<int[]> createSparseArray(int length, double density) {
		long start = System.nanoTime();
		Random num = new Random();
		
		ArrayList<int[]> Sparse_List = new ArrayList<int[]>();
		double random_double = 0.0;
		int random_int = 0;
		
		
		for(int i = 0; i < length; i++) {
			 random_double = num.nextDouble();
			 random_int = num.nextInt(1000000) +1;
			
			if(random_double < density) {
				Sparse_List.add(new int[] {i, random_int});
			} // end if statement
			
		} // end for loop
		
		long end = System.nanoTime() - start;
	 	System.out.println("convert to sparse length: " + Sparse_List.size() + " time: " + end/1000000.0);
	 	
	 	return Sparse_List;
	} // end Sparse Array method
	
	public static ArrayList<int[]> DensityToSparse(int[] densityArray) {
		long start = System.nanoTime();
		
		ArrayList<int[]> equivSparse = new ArrayList<int[]>();
		for(int i = 0; i < densityArray.length; i++) {
			if(densityArray[i] != 0) {
				equivSparse.add(new int[] {i, densityArray[i]});
				
			} // end if statement
		} // end loop 
		
		long end = System.nanoTime() - start;
	 	System.out.println("create sparse length: " + equivSparse.size() + " time: " + end/1000000.0);
	 	
	 	return equivSparse;
	} // end of DensityToSparse method
	
	// PART 5
	public static int[] NewDenseArray(int len, ArrayList<int[]> Sparse_Array) {
		long start = System.nanoTime();
		
		 int [] equivDense = new int[len];
		for(int i = 0; i < Sparse_Array.size(); i++) {
			equivDense[Sparse_Array.get(i)[0]] = Sparse_Array.get(i)[1];
			
		}
		long end = System.nanoTime() - start;
	 	System.out.println("convert to dense length: " + len + " time: " + end/1000000.0);
	 	
	 	return equivDense;
	 	
	}
	
	// PART 6
	public static void maxDense(int[] dense_array) {
		long start = System.nanoTime();
		int value = 0;
		int index = 0;
		for(int i = 0; i < dense_array.length; i++) {
			if(dense_array[i] > value) {
				value = dense_array[i];
				index = i;
			}
		} // end loop
		
		System.out.println("Find max (dense); " + value +" at: " + index);
		long end = System.nanoTime() - start;
	 	System.out.println("dense find time: " + end/1000000.0);
	 	
		
	} // end maxDense method
	
	// PART 7
	
	public static void maxSparse(ArrayList<int[]> sparse_array) {
		long start = System.nanoTime();
		int value = 0;
		int index = 0;
		
		for(int i = 0; i < sparse_array.size(); i++ ) {
			if(sparse_array.get(i)[1] > value) {
				value = sparse_array.get(i)[1];
				index = sparse_array.get(i)[0];
				
			}
		}
		
		System.out.println("Find max (sparse); " + value +" at: " + index);
		long end = System.nanoTime() - start;
	 	System.out.println("sparse find time: " + end/1000000.0);
	 	
	} // end maxSparse method
	
	

	} // end assignment2

// PART 8 TIMING COMMENTS:
// Finding Max Sparse times will always take a much shorter time compared to Max Dense Arrays since 
// the computer will have to sort through much fewer items. 
// When the Density value is low or high, an integer array length of 1000 will yield relatively equal times.
// As the integer array value increases, so does the time it takes for every step, although it takes a much shorter
// time to find Max dense and sparse values. (approx 9 milliseconds compared to 500 for an integer array length
// of 10000000
// When integer array size is large (I tested with size 5000000), all times will increase significantly as the 
// density value is higher. (I tested with density .99 and found it took approx times of 1500+ milliseconds
// to convert to sparse length and create sparse length. 
// Using the same value of 5000000 for array size, and using .001 as a new density value, the times for every case 
// reduced significantly. (approx 200 milliseconds)

// Which implementation is fastest? 
// For optimal timing, use low density values and small array sizes.
	
	



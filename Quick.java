/**
 * This is what I did in the one question in one of our tuts and I thought I might as well use it here, 
 * to sort my high scores
 * @author elan
 *
 */
public class Quick
{
	public static void sort(Comparable[] array)
	{
		quicksort(array, 0, array.length - 1);
	}
	
	private static void quicksort(Comparable[] array, int start, int end)
	{
		if(start >= end) return;
		
		int pivot = partition(array, start, end);
		quicksort(array, start, pivot - 1);
		quicksort(array, pivot + 1, end);
	}
	
	private static int partition(Comparable[] array, int start, int end)
	{
		int pivot = end;
		swap(array, median(array, start, end), end);
		int wall = start;
		
		for(int i = start; i < end; i++)
		{
			if(!(array[i].compareTo(array[pivot]) > 0))
			{
				swap(array, wall, i);
				wall++;
			}
		}
		
		swap(array, pivot, wall);
		return wall;
	}
	
	private static int median(Comparable[] array, int start, int end)
	{
		if(end == start + 1) return end;
		
		int mid = (start+end)/2;
		
		if(array[start].compareTo(array[mid]) > 0 && array[start].compareTo(array[end]) < 0 || array[start].compareTo(array[mid]) < 0 && array[start].compareTo(array[end]) > 0)
			return start;
		
		if(array[mid].compareTo(array[start]) > 0 && array[mid].compareTo(array[end]) < 0 || array[mid].compareTo(array[start]) < 0 && array[mid].compareTo(array[end]) > 0)
			return mid;
		
		return end;
		
	}
	
	private static void swap(Object[] array, int index1, int index2)
	{
		if (index1 == index2) return;
		
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
}

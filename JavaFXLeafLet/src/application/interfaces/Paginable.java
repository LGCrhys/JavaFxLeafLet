package application.interfaces;

public interface Paginable {
	
	void goToPage(int page);
	void changePageSize(int size);
	int getNbOfRecords();

}

package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	public void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	//S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
	public void nImprimePasSiPasAssezDArgent(){
		machine.insertMoney( PRICE -1);
		assertFalse(machine.printTicket(), "Pas assez d'argent, la machine ne doit pas imprimer");
	}

	@Test
	//S4 : on imprime le ticket si le montant est suffisant
	public void imprimeTicketSiBonMontant(){
		machine.insertMoney(PRICE +1);
		assertTrue(machine.printTicket(), "Ticket en impression");
	}

	@Test
	//S5 : Quand on imprime un ticket la balance est decrementee du pris du ticket
	public void decrementePrixTicket(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(machine.getBalance(),machine.getPrice()-PRICE);
	}

	@Test
	//S6 : Le montant collecte est mis a jour quand on imprime un ticket (pas avant)
	public void montantCollecteMisAJour(){
		machine.insertMoney(PRICE);
		assertEquals(PRICE,machine.getBalance());
	}

	@Test
	//S7 : Rend correctement la monnaie
	public void rendMonnaieCorrectement(){
		machine.insertMoney(PRICE);
		assertEquals(PRICE,machine.refund());
	}

	@Test
	//S8 : Remet la balance a zero
	public void remetBalanceZero(){
		machine.insertMoney(PRICE);
		machine.refund();
		assertEquals(0,machine.getBalance());
	}

	@Test
	//S9 : On ne peut pas inserer un montant negatif
	public void pasInsererMontantNegatif(){

	}

	@Test
	//S10 : On ne peut pas creer de machine qui delivre des tickets dont le prix est negatif
	public void pasCreerMachineTicketPrixNegatif(){

	}
}

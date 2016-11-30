/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package gui;

/**
 * This is the main class of this
 * program it starts the program.
 * 
 * @author Colin Casey
 */
public class AuctionCentralMain {
	/**
     * Private constructor, to prevent instantiation of this class.
     */
    private AuctionCentralMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the Auction central GUI. 
     * Command line arguments are ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuctionCentralGUI().start();
            }
        });
    }
}

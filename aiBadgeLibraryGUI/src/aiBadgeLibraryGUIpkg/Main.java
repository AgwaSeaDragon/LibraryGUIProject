package aiBadgeLibraryGUIpkg;

import javax.swing.SwingUtilities;

public class Main
{
	public static void main(String [] args)
	{
		SwingUtilities.invokeLater(LibrarySystem::new);
	}
	
}

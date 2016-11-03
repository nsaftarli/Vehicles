import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Assignment2
{
	public static void main(String[] args)
	{
		/**
		Variable declarations:
		**/
		AssignComp aComp;
		JFrame frame;
		JMenuBar bar;
		JMenu file;
			JMenuItem startOver;
			JMenuItem quitProgram;
		JMenu queue;
			JMenuItem qRemove;
			JMenuItem qAdd;
		JMenu list;
			JMenuItem listAddFirst;
			JMenuItem listAddLast;
			JMenuItem listRemFirst;
			JMenuItem listRemLast;
		/**
		Variable Initializations:
		**/
		aComp = new AssignComp();
		frame = new JFrame();
		bar = new JMenuBar();
		file = new JMenu("File");
			startOver = new JMenuItem("New");
			quitProgram = new JMenuItem("Exit");
			file.add(startOver);
			file.add(quitProgram);

		queue = new JMenu("Queue");
			qRemove = new JMenuItem("Remove");
			qAdd = new JMenuItem("Add");
			queue.add(qRemove);
			queue.add(qAdd);

		list = new JMenu("List");
			listAddFirst = new JMenuItem("Add First");
			listAddLast = new JMenuItem("Add Last");
			listRemFirst = new JMenuItem("Remove First");
			listRemLast = new JMenuItem("Remove Last");
			list.add(listAddFirst);
			list.add(listAddLast);
			list.add(listRemFirst);
			list.add(listRemLast);
		/**
		Add to Menu Bar and Frame:
		**/
		bar.add(file);
		bar.add(queue);
		bar.add(list);
		frame.add(aComp, BorderLayout.CENTER);
		frame.add(bar, BorderLayout.NORTH);
		/**
		Listeners:
		**/
		class AddFirstListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.addFirst();
			}
		}
		ActionListener addFirstListener = new AddFirstListener();
		listAddFirst.addActionListener(addFirstListener);

		class AddLastListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.addLast();
			}
		}
		ActionListener addLastListener = new AddLastListener();
		listAddLast.addActionListener(addLastListener);

		class RemoveLastListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.removeLast();
			}
		}
		ActionListener removeLastListener = new RemoveLastListener();
		listRemLast.addActionListener(removeLastListener);

		class RemoveFirstListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.removeFirst();
			}
		}
		ActionListener removeFirstListener = new RemoveFirstListener();
		listRemFirst.addActionListener(removeFirstListener);

		class ResetProgramListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.reset();
			}
		}
		ActionListener programResetListener = new ResetProgramListener();
		startOver.addActionListener(programResetListener);

		class RemoveBoxListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.removeBox();
			}
		}
		ActionListener removeBoxListener = new RemoveBoxListener();
		qRemove.addActionListener(removeBoxListener);

		class AddBoxListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				aComp.addBox();
			}
		}
		ActionListener addBoxListener = new AddBoxListener();
		qAdd.addActionListener(addBoxListener);

		class ExitProgramListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		}
		ActionListener exitProgramListener = new ExitProgramListener();
		quitProgram.addActionListener(exitProgramListener);

		frame.setSize(800, 800);
		frame.setVisible(true);

	}
}

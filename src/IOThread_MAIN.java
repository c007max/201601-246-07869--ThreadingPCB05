import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections ;

public class IOThread_MAIN
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException
	{
		int nodes_T	= 10 ;
		Random r1 = new Random();
		PCB	pcbRunning = null ;
		
		LinkedList<PCB> QReady	= new LinkedList<PCB>();
		
		for (int ii=0; ii<nodes_T; ii++)
		{
			QReady.add(new PCB());
		}
		
		Collections.sort(QReady);
		
		for (PCB loopI : QReady)
			System.out.printf("***@ sort main: %s\t***\n"	,loopI.showPCB()) ;
		
		while (!QReady.isEmpty())
		{
			pcbRunning	= QReady.removeFirst();

			Thread iop	= new Thread(new IOProcess(Integer.toString(pcbRunning.get_ID())
					,pcbRunning
					,QReady
					));

			iop.start();

			System.out.printf("***\tmain: thread started %s %d %s\t***\n"
					,iop.getName()
					,iop.getId()
					,iop.getState()
					);
		}
		
		while (Thread.activeCount() > 1)
		{
			System.out.printf("***\tmain: threads still running: %d\t***\n"
				,Thread.activeCount()
				);
			
			Thread.sleep(1000);
		}
		
	
		for (PCB loopI : QReady)
			System.out.printf("***main: %s\t***\n"	,loopI.showPCB()) ;

		Collections.sort(QReady);
	
		for (PCB loopI : QReady)
			System.out.printf("***@ sort main: %s\t***\n"	,loopI.showPCB()) ;

		System.out.printf("@@@\tdone\t@@@\n");
	}
}

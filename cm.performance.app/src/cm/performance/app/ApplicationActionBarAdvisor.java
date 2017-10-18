package cm.performance.app;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.cubrid.common.ui.spi.action.ActionManager;
import com.cubrid.cubridmanager.ui.spi.action.CubridActionBuilder;

import cm.performance.action.AddHostAction;
import cm.performance.action.OpenQueryEditorAction;
import cm.performance.action.RunQueryAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private AddHostAction addHostAction;
	private OpenQueryEditorAction openAction;
	private RunQueryAction runQueryAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	CubridActionBuilder.init();
    	
    	addHostAction = new AddHostAction(window);
    	register(addHostAction);
    	openAction = new OpenQueryEditorAction(window);
    	register(openAction);
    	runQueryAction = new RunQueryAction(window);
    	register(runQueryAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
    	coolBar.add(toolbar);
    	toolbar.add(addHostAction);
    	toolbar.add(new Separator());
    	toolbar.add(openAction);
    	toolbar.add(new Separator());
    	toolbar.add(runQueryAction);
    }
}

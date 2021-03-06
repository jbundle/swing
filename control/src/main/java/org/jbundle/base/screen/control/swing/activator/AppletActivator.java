/*
 * RmiSessionServer.java
 *
 * Created on January 10, 2000, 4:47 PM
 
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jbundle.base.screen.control.swing.activator;

import java.util.Map;

import org.jbundle.base.model.Utility;
import org.jbundle.base.screen.control.swing.SApplet;
import org.jbundle.base.util.BaseThickActivator;
import org.jbundle.model.Task;
import org.jbundle.thin.base.screen.splash.Splash;
import org.jbundle.thin.base.util.Application;
import org.osgi.framework.BundleContext;

public class AppletActivator extends BaseThickActivator
{

    /**
     * Start this service.
     * Override this to do all the startup.
     * @param context bundle context
     * @return true if successful.
     */
    public Object startupService(BundleContext bundleContext)
    {
	        Map<String,Object> propertiesTemp = this.getServiceProperties();

	        //?server = new SApplet(args);
	        propertiesTemp.put(Splash.MAIN, SApplet.class.getName());
	        Splash.main(Utility.propertiesToArgs(propertiesTemp));	// Note that I start Main instead of SApplet so SApplet can shutdown this bundle
//	        Main.main(Utility.propertiesToArgs(propertiesTemp));
	        return Application.getRootApplet();

//?    	        Environment env = new Environment(propertiesTemp);
	        // Note the order that I do this... this is because MainApplication may need access to the remoteapp during initialization
    }
    /**
     * Stop this service.
     * Override this to do all the startup.
     * @param bundleService
     * @param context bundle context
     * @return true if successful.
     */
    public boolean shutdownService(Object service, BundleContext context)
    {
    	if (((Task)(service)).getApplication() != null)
    		((Task)(service)).getApplication().free();
    	else
    		((Task)(service)).free();
    	return true;
    }
}

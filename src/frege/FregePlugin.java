package frege;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

public class FregePlugin extends PluginBase {

	public static final String kPluginID = "frege.ide";
	public static final String kLanguageID = "frege";
	private static String fregeLib = null;

	/**
	 * The unique instance of this plugin class
	 */
	protected static FregePlugin sPlugin;

	public static FregePlugin getInstance() {
		if (sPlugin == null)
			new FregePlugin();
		return sPlugin;
	}

	public FregePlugin() {
		super();
		sPlugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * get the path name of the Frege Standard Library
	 */
	public String getFregeLib() {
		if (fregeLib == null) {
			final ProtectionDomain pd = this.getClass().getProtectionDomain();
			final CodeSource cs = pd.getCodeSource();
			URL xurl = cs.getLocation();
			fregeLib = xurl.getPath();
			System.err.println(kPluginID + ": " + xurl);
		}
		return fregeLib;
	}

	@Override
	public String getID() {
		return kPluginID;
	}

	@Override
	public String getLanguageID() {
		return kLanguageID;
	}

	// Definitions for image management

	public static final org.eclipse.core.runtime.IPath ICONS_PATH = new org.eclipse.core.runtime.Path(
			"icons/"); //$NON-NLS-1$("icons/"); //$NON-NLS-1$

	protected void initializeImageRegistry(
			org.eclipse.jface.resource.ImageRegistry reg) {
		org.osgi.framework.Bundle bundle = getBundle();
		org.eclipse.core.runtime.IPath path = ICONS_PATH
				.append("frege_default_image.gif");//$NON-NLS-1$
		org.eclipse.jface.resource.ImageDescriptor imageDescriptor = createImageDescriptor(
				bundle, path);
		reg.put(IFregeResources.FREGE_DEFAULT_IMAGE, imageDescriptor);

		path = ICONS_PATH.append("frege_default_outline_item.gif");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_DEFAULT_OUTLINE_ITEM, imageDescriptor);

		path = ICONS_PATH.append("frege_file.gif");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE, imageDescriptor);

		path = ICONS_PATH.append("frege_file_warning.gif");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE_WARNING, imageDescriptor);

		path = ICONS_PATH.append("frege_file_error.gif");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE_ERROR, imageDescriptor);
	}

	public static org.eclipse.jface.resource.ImageDescriptor createImageDescriptor(
			org.osgi.framework.Bundle bundle,
			org.eclipse.core.runtime.IPath path) {
		java.net.URL url = org.eclipse.core.runtime.FileLocator.find(bundle,
				path, null);
		if (url != null) {
			return org.eclipse.jface.resource.ImageDescriptor
					.createFromURL(url);
		}
		return null;
	}

	// Definitions for image management end

}

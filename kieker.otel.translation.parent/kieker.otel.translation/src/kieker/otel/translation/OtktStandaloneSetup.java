/*
 * generated by Xtext 2.36.0
 */
package kieker.otel.translation;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class OtktStandaloneSetup extends OtktStandaloneSetupGenerated {

	public static void doSetup() {
		new OtktStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

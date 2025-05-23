package kieker.otel.translation.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

import kieker.otel.translation.otkt.KiekerReference;
import kieker.otel.translation.otkt.NewRecord;
import kieker.otel.translation.otkt.OtktPackage;
import kiekerrecord.KiekerRecord;

public class KiekerReferenceScopeProvider extends AbstractDeclarativeScopeProvider {

	public IScope getScope(EObject context, EReference reference) {
		if (context instanceof KiekerReference && 
				reference == OtktPackage.Literals.KIEKER_REFERENCE__REF_ATTRIBUTE) {
			KiekerReference crossRef = (KiekerReference) context;
			EObject refEntity = crossRef.getRefEntity();
			if (refEntity instanceof NewRecord) {
				if (refEntity != null) {
					// Provide a scope containing the attributes of the referenced entity
					// if(refEntity instanceof NewRecord)

					IScope result = Scopes.scopeFor(((NewRecord) refEntity).getAttributes());
					return result;
				}
			} else if (refEntity instanceof KiekerRecord) {
				if (refEntity != null) {
					// Provide a scope containing the attributes of the referenced entity
					// if(refEntity instanceof NewRecord)

					IScope result = Scopes.scopeFor(((KiekerRecord) refEntity).getAttributes());
					return result;
				}
				
			}
			
		}
		
		
		return super.getScope(context, reference);
	}
}

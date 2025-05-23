/*
 * generated by Xtext 2.36.0
 */
package kieker.otel.translation.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;

import kieker.otel.translation.otkt.CustomMapping;
import kieker.otel.translation.otkt.DefaultAttributes;
import kieker.otel.translation.otkt.KiekerReference;
import kieker.otel.translation.otkt.MappingRule;
import kieker.otel.translation.otkt.NewRecord;
import kieker.otel.translation.otkt.OtelRef;
import kieker.otel.translation.otkt.OtelSpan;
import kieker.otel.translation.otkt.OtktPackage;
import kieker.otel.translation.otkt.RecordAttribute;
import kieker.otel.translation.otkt.SpanAttribute;
import kieker.otel.translation.util.Util;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class OtktValidator extends AbstractOtktValidator {
	
//	public static final String INVALID_NAME = "invalidName";
//
	/*@Check
	public void checkGreetingStartsWithCapital(Greeting greeting) {
		if (!Character.isUpperCase(greeting.getName().charAt(0))) {
			warning("Name should start with a capital",
					OtktPackage.Literals.GREETING__NAME,
					INVALID_NAME);
		}
	}*/
	
	@Check
	public void checkMappingRuleForNormalRecord(MappingRule rule) {
		OtelRef left = rule.getLeftSide();
		KiekerReference right = rule.getRightSide();

		//OtelSpan otelSpan = (OtelSpan) left.getRefEntity();
		if (right.getRefEntity() instanceof NewRecord && left.getRefAttribute() instanceof SpanAttribute
				&& right.getRefAttribute() instanceof RecordAttribute) {
		NewRecord record = (NewRecord) right.getRefEntity();
		SpanAttribute otelParam = (SpanAttribute) left.getRefAttribute();
		RecordAttribute kiekerParam = (RecordAttribute) right.getRefAttribute();
		
		if (record.getType().getLiteral().equals("normal")) {
			if (!otelParam.getType().getLiteral().equals(kiekerParam.getType().getLiteral())) {
				if(otelParam.getType().getLiteral().equals("default")) {
					DefaultAttributes deffAttr=(DefaultAttributes)otelParam.eContainer();
					String type = Util.getTypeOfDefaultAttr(deffAttr);
					if(!type.equals(kiekerParam.getType().getLiteral())) {
						error("Types of OpenTelemtry Span and Kieker Record must match!",
								OtktPackage.Literals.MAPPING_RULE__LEFT_SIDE);
						error("Types of OpenTelemtry Span and Kieker Record must match!",
								OtktPackage.Literals.MAPPING_RULE__RIGHT_SIDE);
					}
				}else {
				
					error("Types of OpenTelemtry Span and Kieker Record must match!",
							OtktPackage.Literals.MAPPING_RULE__LEFT_SIDE);
					error("Types of OpenTelemtry Span and Kieker Record must match!",
							OtktPackage.Literals.MAPPING_RULE__RIGHT_SIDE);
			}
		}

	}
		}
	}
	
	@Check
	public void checkMappingRuleForFlow(MappingRule rule) {
		OtelRef left = rule.getLeftSide();
		KiekerReference right = rule.getRightSide();
		
		if (right.getRefEntity() instanceof NewRecord && left.getRefAttribute() instanceof SpanAttribute
				&& right.getRefAttribute() instanceof RecordAttribute) {
			NewRecord record = (NewRecord) right.getRefEntity();

			SpanAttribute otelParam = (SpanAttribute) left.getRefAttribute();
			RecordAttribute kiekerParam = (RecordAttribute) right.getRefAttribute();

			if (record.getType().getLiteral().equals("flow")) {
				if (otelParam.eContainer() instanceof DefaultAttributes) {
					error("Specifying custom mappings for default Span Attributes is not allowed if you map them to flow record!",
							OtktPackage.Literals.CUSTOM_MAPPING__MAPPING_RULES);
				} else if (!otelParam.getType().getLiteral().equals(kiekerParam.getType().getLiteral())) {
					error("Types of OpenTelemtry Span and Kieker Record must match!",
							OtktPackage.Literals.MAPPING_RULE__LEFT_SIDE);
					error("Types of OpenTelemtry Span and Kieker Record must match!",
							OtktPackage.Literals.MAPPING_RULE__RIGHT_SIDE);
				}
			}
		}
	}
	
			
//	public void check

	@Check
	public void checkDefaultSpanAttributeType(DefaultAttributes attr) {
		SpanAttribute trace = attr.getTrace();
		SpanAttribute startT = attr.getStartTime();
		SpanAttribute finishT = attr.getEndTime();
		SpanAttribute parentSpan = attr.getParentTrace();
		SpanAttribute span = attr.getSpanId();

		if (trace != null) {
			if (!(trace.getType().getLiteral().equals("string")||trace.getType().getLiteral().equals("default")) ) {
				error("Trace must be a String or a Default! Correct your type specification or leave it blank!",
						OtktPackage.Literals.DEFAULT_ATTRIBUTES__TRACE);
			}
		}

		if (startT != null) {
			if (!(startT.getType().getLiteral().equals("long")||startT.getType().getLiteral().equals("default"))) {
				error("startT must be a Long or a Deafult! Correct your type specification or leave it blank!",
						OtktPackage.Literals.DEFAULT_ATTRIBUTES__START_TIME);
			}
		}

		if (finishT != null) {
			if (!(startT.getType().getLiteral().equals("long")||finishT.getType().getLiteral().equals("default"))) {
				error("endT must be a Long or a Deafult! Correct your type specification or leave it blank!",
						OtktPackage.Literals.DEFAULT_ATTRIBUTES__END_TIME);
			}
		}

		if (parentSpan != null) {
			if (!(parentSpan.getType().getLiteral().equals("long")||parentSpan.getType().getLiteral().equals("default"))) {
				error("parentSpan must be a Long or a Deafult! Correct your type specification or leave it blank!",
						OtktPackage.Literals.DEFAULT_ATTRIBUTES__PARENT_TRACE);
			}
		}

		if (span != null) {
			if (!(span.getType().getLiteral().equals("long")||span.getType().getLiteral().equals("default"))) {
				error("Span must be a Long or a Deafult! Correct your type specification or leave it blank!",
						OtktPackage.Literals.DEFAULT_ATTRIBUTES__SPAN_ID);
			}
		}
	}
	@Check
	public void checkMappingType(CustomMapping mapping) {
		OtelSpan from  =mapping.getFrom();
		EObject to = mapping.getTo();
		
		EList<MappingRule> mappings =mapping.getMappingRules();
		for(MappingRule rule : mappings) {
			OtelRef left = rule.getLeftSide();
			KiekerReference right = rule.getRightSide();
			
			if (left.getRefEntity() instanceof OtelSpan) {
				OtelSpan span = (OtelSpan) left.getRefEntity();
				if (!span.getName().equals(from.getName())) {
					error("The mapped left attributes must come from Span named " + from.getName(),
							OtktPackage.Literals.CUSTOM_MAPPING__MAPPING_RULES);
				}
			}
			if (right.getRefEntity() instanceof NewRecord) {
				NewRecord record = (NewRecord) right.getRefEntity();
				if (!record.getName().equals(((NewRecord)to).getName())) {
					error("The mapped right attributes must come from Record named " + ((NewRecord)to).getName(),
							OtktPackage.Literals.CUSTOM_MAPPING__MAPPING_RULES);
				}
			}
			
		}
	}
	
	
		
	
}

/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.validator.internal.metadata.raw;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.internal.engine.valuehandling.UnwrapMode;
import org.hibernate.validator.internal.metadata.core.MetaConstraint;

/**
 * Represents a field of a Java type and all its associated meta-data relevant
 * in the context of bean validation, for instance its constraints.
 *
 * @author Gunnar Morling
 */
public class ConstrainedField extends AbstractConstrainedElement {

	private final Field field;
	private final Set<MetaConstraint<?>> typeArgumentsConstraints;

	/**
	 * Creates a new field meta data object.
	 *
	 * @param source The source of meta data.
	 * @param field The represented field.
	 * @param constraints The constraints of the represented field, if any.
	 * @param typeArgumentsConstraints Type arguments constraints, if any.
	 * @param groupConversions The group conversions of the represented field, if any.
	 * @param isCascading Whether a cascaded validation of the represented field shall
	 * be performed or not.
	 * @param unwrapMode Determines how the value of the field must be handled in regards to
	 * unwrapping prior to validation.
	 */
	public ConstrainedField(ConfigurationSource source,
							Field field,
							Set<MetaConstraint<?>> constraints,
							Set<MetaConstraint<?>> typeArgumentsConstraints,
							Map<Class<?>, Class<?>> groupConversions,
							boolean isCascading,
							UnwrapMode unwrapMode) {

		super( source, ConstrainedElementKind.FIELD, constraints, groupConversions, isCascading, unwrapMode );

		this.field = field;
		this.typeArgumentsConstraints = typeArgumentsConstraints != null ? Collections.unmodifiableSet(
				typeArgumentsConstraints
		) : Collections.<MetaConstraint<?>>emptySet();
	}

	public Field getField() {
		return field;
	}

	public Set<MetaConstraint<?>> getTypeArgumentsConstraints() {
		return this.typeArgumentsConstraints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( field == null ) ? 0 : field.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !super.equals( obj ) ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		ConstrainedField other = (ConstrainedField) obj;
		if ( field == null ) {
			if ( other.field != null ) {
				return false;
			}
		}
		else if ( !field.equals( other.field ) ) {
			return false;
		}
		return true;
	}
}

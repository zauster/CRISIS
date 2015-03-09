/*
 * This file is part of CRISIS, an economics simulator.
 * 
 * Copyright (C) 2015 AITIA International, Inc.
 *
 * CRISIS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CRISIS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CRISIS.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.crisis_economics.abm.annotation;


/**
 * This annotation should decorate Collection or Map members of classes with a {@link Recorder} annotation. When such a collection or map is not
 * declared as a generic type, the type of the elements contained in the collection or map is not detectable. In such cases, this annotation should be
 * used to notify the recording framework about the types to expect in the collection or map.
 * 
 * @author Tamás Máhr
 * 
 */
public @interface ContainsRecorderSource {
	Class<?> value();
}

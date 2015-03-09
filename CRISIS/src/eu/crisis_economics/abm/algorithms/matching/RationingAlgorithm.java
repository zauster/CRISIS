/*
 * This file is part of CRISIS, an economics simulator.
 * 
 * Copyright (C) 2015 Ross Richardson
 * Copyright (C) 2015 John Kieran Phillips
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
package eu.crisis_economics.abm.algorithms.matching;

import java.util.Collection;
import java.security.InvalidAlgorithmParameterException;

/**
  * A simple interface for algorithms that ration two groups of nodes in 
  * such a way that supply (the left group offering) equals demand (the
  * right group demand).
  * 
  * @author phillips
  */
public interface RationingAlgorithm {
    void rationNodes(
        Collection<ComputeNode> leftNodes,   // Modified
        Collection<ComputeNode> rightNodes   // Modified
        ) throws InvalidAlgorithmParameterException;
}

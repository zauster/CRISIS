/*
 * This file is part of CRISIS, an economics simulator.
 * 
 * Copyright (C) 2015 John Kieran Phillips
 * Copyright (C) 2015 Daniel Tang
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
package eu.crisis_economics.abm.markets.nonclearing;

public class InstrumentException extends Exception {

	private static final long serialVersionUID = -6935194023716272465L;
	String err;
	//----------------------------------------------
	// Default constructor - initializes instance variable to unknown
	  public InstrumentException()
	  {
	    super();             // call superclass constructor
	    err = "unknown";
	  }
	  
	//-----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance variable.
	  public InstrumentException(final String err)
	  {
	    super(err);     // call super class constructor
	    this.err = err;  // save message
	  }
	  
	//------------------------------------------------  
	// public method, callable by exception catcher. It returns the error message.
	  public String getError()
	  {
	    return err;
	  }

}

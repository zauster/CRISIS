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
package eu.crisis_economics.abm.firm;

import eu.crisis_economics.abm.contracts.DepositHolder;
import eu.crisis_economics.abm.contracts.stocks.StockHolder;

/**
  * @author phillips
  */
public interface FirmFactory {
   /**
     * Create a {@link LoanStrategyFirm} object.
     * 
     * @param depositHolder
     *        The {@link DepositHolder} who manages the {@link Firm} deposit account.
     * @param stockHolder
     *        The {@link StockTradingBank} who, initially, receives {@link Firm} shares.
     * @param numberOfShares
     *        The number of shares to release to the {@link StockTradingBank}.
     */
   public LoanStrategyFirm create(
      DepositHolder depositHolder,
      StockHolder stockHolder,
      double numberOfShares
      );
}

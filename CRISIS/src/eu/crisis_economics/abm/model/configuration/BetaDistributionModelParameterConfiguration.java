/*
 * This file is part of CRISIS, an economics simulator.
 * 
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
package eu.crisis_economics.abm.model.configuration;

import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import eu.crisis_economics.abm.model.ConfigurationComponent;
import eu.crisis_economics.abm.model.Layout;
import eu.crisis_economics.abm.model.Parameter;
import eu.crisis_economics.abm.simulation.injection.factories.InjectionFactoryUtils;

/**
  * A {@link ComponentConfiguration} for a {@link RealDistribution} implemented
  * by the scaled Beta distribution.<br><br>
  * 
  * This {@link ComponentConfiguration} provides a {@link Parameter}{@code <Double>}
  * implemented by a Beta distribution. The profile of this Beta distribution is customizable.
  * <br><br>
  * 
  * The domain of this distribution is not limited to the range {@code [0, 1]}. Arbitrary 
  * domains are created by linearly upscaling a Beta distribution sampled in the unit interval.
  * In particular, all {@link Parameter} values created by this {@link ComponentConfiguration}
  * yield samples of the following form:
  * 
  * <center><code>
  *   sample = min + (max - min) * Beta(alpha, beta)
  * </code></center><br>
  * 
  * Where {@code sample} is the observed parameter value, {@code min} is the lower 
  * bound on sample values, {@code max} is the upper bound on sample values,
  * and {@code alpha}, {@code beta} are the standard Beta distribution shape parameters.
  * 
  * @author phillips
  */
@ConfigurationComponent(
   DisplayName = "Beta Distribution"
   )
public final class BetaDistributionModelParameterConfiguration
   extends AbstractDistributionModelParameterConfiguration
   implements SampledDoubleModelParameterConfiguration,
      TimeseriesDoubleModelParameterConfiguration {
   
   private static final long serialVersionUID = -3171212746496164722L;
   
   private static final double
      DEFAULT_ALPHA = 0.,
      DEFAULT_BETA = 1.,
      DEFAULT_MINIMUM = 0.,
      DEFAULT_MAXIMUM = 1.;
   
   @Layout(
      FieldName = "Alpha",
      Order = 1.0
      )
   @Parameter(
      ID = "BETA_DISTRIBUTION_ALPHA"
      )
   private double
      alpha;
   @Layout(
      FieldName = "Beta",
      Order = 1.1
      )
   @Parameter(
      ID = "BETA_DISTRIBUTION_BETA"
      )
   private double
      beta;
   @Layout(
      FieldName = "Minimum",
      Order = 1.2
      )
   @Parameter(
      ID = "BETA_DISTRIBUTION_MINIMUM"
      )
   private double
      minimum;
   @Layout(
      FieldName = "Maximum",
      Order = 1.3
      )
   @Parameter(
      ID = "BETA_DISTRIBUTION_MAXIMUM"
      )
   private double
      maximum;
   
   public double getAlpha() {
      return alpha;
   }
   
   /**
     * Set the first shape parameter for Beta distributions generated by this 
     * {@link ConfigurationComponent}. The argument must be strictly positive.
     */
   public void setAlpha(
      final double alpha) {
      this.alpha = alpha;
   }
   
   public double getBeta() {
      return beta;
   }
   
   /**
     * Set the second shape parameter for Beta distributions generated by this 
     * {@link ConfigurationComponent}. The argument must be strictly positive.
     */
   public void setBeta(
      final double beta) {
      this.beta = beta;
   }
   
   public double getMinimum() {
      return minimum;
   }
   
   public void setMinimum(
      final double minimum) {
      this.minimum = minimum;
   }
   
   public double getMaximum() {
      return maximum;
   }
   
   public void setMaximum(
      final double maximum) {
      this.maximum = maximum;
   }
   
   /**
     * Create a {@link BetaDistributionModelParameterConfiguration} object with default
     * parameters.
     */
   public BetaDistributionModelParameterConfiguration() {
      this(DEFAULT_ALPHA, DEFAULT_BETA, DEFAULT_MINIMUM, DEFAULT_MAXIMUM);
   }
   
   /**
     * Create a {@link BetaDistributionModelParameterConfiguration} object with custom
     * parameters. See also {@link BetaDistributionModelParameterConfiguration}.
     * 
     * @param alpha
     *        The first shape parameter for a Beta distribution. This argument must
     *        be strictly positive.
     * @param beta
     *        The second shape parameter for a Beta distribution. This argument must
     *        be strictly positive.
     * @param minimum
     *        The minimum in the range of the distribution.
     * @param maximum
     *        The maximum in the range of the distribution. This argument must not be 
     *        less than {@code minimum}.
     */
   public BetaDistributionModelParameterConfiguration(
      final double alpha,
      final double beta,
      final double minimum,
      final double maximum
      ) {
      Preconditions.checkArgument(beta > 0.);
      Preconditions.checkArgument(alpha > 0.);
      Preconditions.checkArgument(maximum >= minimum);
      this.alpha = alpha;
      this.beta = beta;
      this.minimum = minimum;
      this.maximum = maximum;
   }
   
   /**
     * Create a {@link BetaDistributionModelParameterConfiguration} object from
     * a mean and variance measurement. The mean and variance specified are converted
     * to a pair of shape parameter for the Beta distribution.
     * 
     * @param mean
     *        The Beta distribution mean. This argument should be strictly positive and 
     *        less than {@code 1.0}.
     * @param variance
     *        The Beta distribution variance. This argument should be strictly positive.
     * @param minimum
     *        The minimum in the range of the distribution.
     * @param maximum
     *        The maximum in the range of the distribution. This argument must not be 
     *        less than {@code minimum}.
     */
   public static BetaDistributionModelParameterConfiguration
      fromMeanAndVariance(
         final double mean,
         final double variance,
         final double minimum,
         final double maximum
         ) {
      final double
         delta = (maximum - minimum),
         mu = (mean - minimum) / delta,
         var = variance / (delta * delta),
         alpha = -(mu * ((-1. + mu) * mu + var))/var,
         beta = -1. + mu + (Math.pow(-1. + mu, 2.) * mu)/var;
      return new BetaDistributionModelParameterConfiguration(alpha, beta, minimum, maximum);
   }
   
   @Override
   public void assertParameterValidity() {
      Preconditions.checkArgument(alpha > 0.,
         toParameterValidityErrMsg("Alpha must be strictly positive."));
      Preconditions.checkArgument(alpha > 0.,
         toParameterValidityErrMsg("Beta must be strictly positive."));
      Preconditions.checkArgument(maximum >= minimum,
         toParameterValidityErrMsg("Domain maximum must not be less than domain minimum."));
   }
   
   /**
     * A lightweight injection wrapper for the {@link BetaDistribution} class.
     * 
     * @author phillips
     */
   private static final class BetaDistributionImpl extends BetaDistribution {
      private final double
         minimum,
         delta;
      
      @Inject
      BetaDistributionImpl(
      @Named("DISTRIBUTION_ALPHA")
         final double alpha,
      @Named("DISTRIBUTION_BETA")
         final double beta,
      @Named("DISTRIBUTION_MINIMUM")
         final double minimum,
      @Named("DISTRIBUTION_MAXIMUM")
         final double maximum
         ) {
         super(alpha, beta);
         this.minimum = minimum;
         this.delta = (maximum - minimum);
      }
      
      @Override
      public double sample() {
         return delta * super.sample() + minimum;
      }
      
      private static final long serialVersionUID = 3226183917303107496L;
   }
   
   @Override
   protected void addBindings() {
      super.addBindings();
      install(InjectionFactoryUtils.asPrimitiveParameterModule(
         "BETA_DISTRIBUTION_ALPHA",      alpha,
         "BETA_DISTRIBUTION_BETA",       beta,
         "BETA_DISTRIBUTION_MINIMUM",    minimum,
         "BETA_DISTRIBUTION_MAXIMUM",    maximum
         ));
      bind(RealDistribution.class)
         .to(BetaDistributionImpl.class);
   }
}

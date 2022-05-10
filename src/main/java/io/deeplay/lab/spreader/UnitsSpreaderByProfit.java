package io.deeplay.lab.spreader;

import io.deeplay.lab.data.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitsSpreaderByProfit {
     private class MutableDouble {
          double value;

          public MutableDouble(double value) {
               this.value = value;
          }

          public MutableDouble addAndGet(double value) {
               return new MutableDouble(this.value += value);
          }

          public void sub(double value) {
               this.value -= value;
          }
     }

     private class TotalProfit {
          Location location;
          List<MutableDouble> profits;

          public TotalProfit(Map.Entry<Location, List<Double>> prediction) {
               location = prediction.getKey();
               var sum = new MutableDouble(0.0);
               profits = prediction.getValue().stream()
                       .map(sum::addAndGet)
                       .collect(Collectors.toCollection(ArrayList::new));
          }
     }

     private record ReduceResult (
             Location location,
             int unitsCount
     ) {
     }


     private List<TotalProfit> convertPredictionsToTotalProfits(
             Map<Location, List<Double>> predictions
     ) {
          return predictions
             .entrySet()
             .stream()
             .parallel()
             .map(TotalProfit::new)
             .collect(Collectors.toCollection(ArrayList::new));
     }


     private void reduceTotalProfits(
             List<TotalProfit> totals,
             TotalProfit maxProfitTotal,
             double maxProfit,
             int unitsCount
     ) {
          if (maxProfitTotal.profits.size() == unitsCount) {
               totals.remove(maxProfitTotal);
          } else {
               var max = 0.0;
               for (var i = 0; i < unitsCount; ++i) {
                    max = maxProfitTotal.profits.remove(0).value;
               }
               var maxFinal = max;
               maxProfitTotal.profits.forEach(v -> v.sub(maxFinal));
          }
     }


     private ReduceResult reduceMostValuableTotalProfit(
             List<TotalProfit> totalProfits,
             int unitsCount
     ) {
          var maxProfit = Double.NEGATIVE_INFINITY;
          var maxProfitTotal = totalProfits.get(0);
          var maxProfitUnitsCount = 0;


          for (var totalProfit : totalProfits) {
               var profits = totalProfit.profits;

               for (var i = 0; i < profits.size() && i < unitsCount; ++i) {
                    var profit = profits.get(i).value / (double) (i + 1);

                    if (profit > maxProfit) {
                         maxProfit = profit;
                         maxProfitTotal = totalProfit;
                         maxProfitUnitsCount = i + 1;
                    }
               }
          }

          if (maxProfit < 0.0) return null;

          reduceTotalProfits(totalProfits, maxProfitTotal, maxProfit, maxProfitUnitsCount);

          return new ReduceResult(maxProfitTotal.location, maxProfitUnitsCount);
     }


     public Map<Location, Integer> spreadUnits(Map<Location, List<Double>> predictions, int unitsCount) {
          var totalProfits = convertPredictionsToTotalProfits(predictions);

          Map<Location, Integer> unitCountsByLocation = new HashMap<>();

          while (totalProfits.isEmpty() == false) {
               var result = reduceMostValuableTotalProfit(totalProfits, unitsCount);
               if (result == null) break;
               unitsCount -= unitCountsByLocation
                       .compute(result.location(),
                               (k, v) -> (v != null ? v : 0) + result.unitsCount());
          }

          return  unitCountsByLocation;
     }
}

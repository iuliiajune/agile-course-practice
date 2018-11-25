package ru.unn.agile.depositconverter.viewmodel;

import ru.unn.agile.depositconverter.model.AccruedInterest;
import ru.unn.agile.depositconverter.model.DepositCalculator;
import ru.unn.agile.depositconverter.model.FrequencyOfCapitalization;

public class DepositCalculatorViewModel {
    private DepositCalculator model; // = new DepositCalculator();
    private double depositAmountViewModel; //  = model.getDepositAmount();
    private int termPlacemantViewModel; //  = model.getTermPlacementInMonths();
    private double interestRateViewModel; // =model.getInterestRate();
    private AccruedInterest accruedInterestViewModel;
    private FrequencyOfCapitalization frequencyOfCapitalizationViewModel;
    private String revenueViewModel;
    private String incomeViewModel;
    private boolean calculatorButtonEnabled = true;
    private static final double HUNDRED = 100;

    public DepositCalculatorViewModel() {
        model = new DepositCalculator();
        depositAmountViewModel = model.getDepositAmount();
        termPlacemantViewModel = model.getTermPlacementInMonths();
        interestRateViewModel = model.getInterestRate();
        accruedInterestViewModel = model.getAccruedInterest();
        frequencyOfCapitalizationViewModel = model.getFrequencyOfCapitalization();
    }

    private double round(final double roundedNum) {
        return Math.round(roundedNum * HUNDRED) / HUNDRED;
    }

    public Boolean isCalculatorButtonEnabled() {
        return calculatorButtonEnabled;
    }

    public void setDepositAmount(final String tmpDepositAmount) {
        if ("".equals(tmpDepositAmount)) {
            calculatorButtonEnabled = false;
            return;
        }
        if (!tmpDepositAmount.matches("\\d+")) {
            calculatorButtonEnabled = false;
            throw new NumberFormatException("Error. Некорректная сумма");
        } else {
            depositAmountViewModel = Double.parseDouble(tmpDepositAmount);
            calculatorButtonEnabled = true;
        }
    }

    public void calculate() {
        model.setDepositAmount(depositAmountViewModel);
        model.setTermPlacementInMonths(termPlacemantViewModel);
        model.setInterestRate(interestRateViewModel);
        model.setFrequencyOfCapitalization(frequencyOfCapitalizationViewModel);
        model.setAccruedInterest(accruedInterestViewModel);
        revenueViewModel = String.valueOf((round(model.calculateRevenue())));
        incomeViewModel = String.valueOf((round(model.getIncome())));
    }

    public String getRevenueWhenAddToDeposit() {
        return revenueViewModel;
    }

    public double getDepositAmountView() {
        return depositAmountViewModel;
    }

    public int getTermPlacementView() {
        return termPlacemantViewModel;
    }

    public void setTermPlacement(final String tmpTermPlacement) {
        if ("".equals(tmpTermPlacement)) {
            calculatorButtonEnabled = false;
            return;
        }
        if (!tmpTermPlacement.matches("\\d+")) {
            calculatorButtonEnabled = false;
            throw new NumberFormatException("Error. Некорроктный срок");
        } else {
            termPlacemantViewModel = Integer.parseInt(tmpTermPlacement);
            calculatorButtonEnabled = true;
        }
    }

    public double getInterestRateView() {
        return interestRateViewModel;
    }

    public void setInterestRate(final String tmpInterestRate) {
        if ("".equals(tmpInterestRate)) {
            calculatorButtonEnabled = false;
            return;
        }
        if (!tmpInterestRate.matches("\\d+")) {
            calculatorButtonEnabled = false;
            throw new NumberFormatException("Error. Некорроктный процент");
        } else {
            interestRateViewModel = Double.parseDouble(tmpInterestRate);
            calculatorButtonEnabled = true;
        }
    }

    public AccruedInterest getAccruedInterest() {
        return accruedInterestViewModel;
    }

    public void setAccruedInterest(final String acInt) {
        if ("addToDeposit".equals(acInt)) {
            accruedInterestViewModel = AccruedInterest.addToDeposit;
        } else {
            accruedInterestViewModel = AccruedInterest.payOut;
        }
    }

    public FrequencyOfCapitalization getFrequencyOfCapitalization() {
        return frequencyOfCapitalizationViewModel;
    }

    public void setFrequencyOfCapitalization(final String frequency) {
        switch (frequency) {
            case "onceMonth":
                frequencyOfCapitalizationViewModel = FrequencyOfCapitalization.onceMonth;
                break;
            case "onceTwoMonth":
                frequencyOfCapitalizationViewModel = FrequencyOfCapitalization.onceTwoMonth;
                break;
            case "quarterly":
                frequencyOfCapitalizationViewModel = FrequencyOfCapitalization.quarterly;
                break;
            case "halfYear":
                frequencyOfCapitalizationViewModel = FrequencyOfCapitalization.halfYear;
                break;
            default:
                frequencyOfCapitalizationViewModel = FrequencyOfCapitalization.onceMonth;
                break;
        }

    }

    public String getIncomeViewModel() {
        return incomeViewModel;
    }

}

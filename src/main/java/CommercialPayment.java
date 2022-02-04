public class CommercialPayment extends PaymentCreation {
    /**
     * Создать платёж контрагенту
     **/
    public void createCommercialPayment(String amount, String payerPartialName, String payeePartialName) {
        typeSum(amount);
        selectPayer(payerPartialName);
        selectPayee(payeePartialName);
        clickCreateButton();
        clickSaveButton();
    }
}

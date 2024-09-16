package org.dto.payment;

import lombok.Data;

@Data
public class PaymentDto {

    private String id;
    private String event;
    private String dateCreated;
    private Payment payment;

    @Data
    public class Payment {
        private String object;
        private String id;
        private String dateCreated;
        private String customer;
        private String paymentLink;
        private Double value;
        private Double netValue;
        private String originalValue;
        private String interestValue;
        private String description;
        private String billingType;
        private String pixTransaction;
        private String status;
        private String dueDate;
        private String originalDueDate;
        private String paymentDate;
        private String clientPaymentDate;
        private String installmentNumber;
        private String invoiceUrl;
        private String invoiceNumber;
        private String externalReference;
        private Boolean deleted;
        private Boolean anticipated;
        private Boolean anticipable;
        private String creditDate;
        private String estimatedCreditDate;
        private String transactionReceiptUrl;
        private String nossoNumero;
        private String bankSlipUrl;
        private String lastInvoiceViewedDate;
        private String lastBankSlipViewedDate;
        private Discount discount;
        private Fine fine;
        private Interest interest;
        private Boolean postalService;
        private String custody;

    }

    @Data
    public class Interest {
        Double value;
        String type;
    }

    @Data
    public class Fine {
        Double value;
        String type;
    }

    @Data
    public class Discount {
        Double value;
        String limitDate;
        Double dueDateLimitDays;
        String type;
    }

}

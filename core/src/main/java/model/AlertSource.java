package model;

public enum AlertSource {
    CTA_CUSTOMER_ALERTS {
        @Override
        public String toString(){
            return "CTA Customer Alerts";
        }
    },

    CTA_TRAIN_TRACKER {
        @Override
        public String toString(){
            return "CTA Train Tracker";
        }
    };
}

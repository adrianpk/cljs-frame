(ns pulap-client.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [pulap-client.core-test]))

(doo-tests 'pulap-client.core-test)

(ns pulap-client.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [pulap-client.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

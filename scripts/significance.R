#!/usr/bin/env Rscript

suppressPackageStartupMessages(library("coin"))
suppressPackageStartupMessages(library(MBESS))

# See http://yatani.jp/HCIstats/TTest
run_t_test <- function(a, b) {
  cat("a =", a, "\n")
  cat("length(a) =", length(a), "\n")
  cat("mean(a) =", mean(a), "\n")
  cat("sd(a) =", sd(a), "\n")
  cat("\n")
  cat("b =", b, "\n")
  cat("length(b) =", length(b), "\n")
  cat("mean(b) =", mean(b), "\n")
  cat("sd(b) =", sd(b), "\n")
  cat("\n")

  t_test_result <- t.test(a, b, paired = TRUE, alternative =
                          "two.sided")
  show(t_test_result)
  t_test_result
}

normality_test <- function(a) {
  qqnorm(a)
  shapiro.test(a)
}

cascade_task_completion_times <- c(31, 21, 41, 47, 25, 37, 20, 13, 23, 23, 35,
                                   19)
julia_task_completion_times <- c(33, 22, 42, 43, 41, 31, 27, 39, 41, 54, 44, 50)

cat("a = cascade_task_completion_times\n")
cat("b = julia_task_completion_times\n")
cat("t-test of task completion times:\n")
t_test_result <- run_t_test(cascade_task_completion_times,
                            julia_task_completion_times)

normality_test(cascade_task_completion_times)
normality_test(julia_task_completion_times)

effect_size <- abs(smd(cascade_task_completion_times,
                       julia_task_completion_times))

cat(sprintf("With a Welch's t test, we found a significant effect for techniques
            (t(%f) = %f, p = %f < 0.05, Cohen's d = %f) with Cascade
             outperforming Julia.\n", t_test_result$parameter,
             abs(t_test_result$statistic), t_test_result$p.value, effect_size))


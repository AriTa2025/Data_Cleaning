1. Identify each problem the cleaner fixed in rows 102, 103, 104, 109 and 110 of the raw data. [3]
   Problem in 102 is date, height 5.9ft, steps 7,900
   Problem in 103 is 48000 steps
   Problem in 104 is age - 17, null tired
   Problem in 109 is date, height is in ft, tired is an outlier 42
   Problem in 110 is age is 170

2. Explain why removeDuplicates must run before imputeMedian and capOutliers. [2]
   removeDuplicates has to run first to get rid of the unnecessary data that may affect how data is counted later for example in median as it takes all the numbers.
   Therefore it must be run to avoid miscalculation.

3. Row 103 had 48 000 steps. Discuss whether capping was the right decision, or whether the value should have been deleted or kept unchanged. [3]
   Deleting or throwing the data away wouldn't just change steps but also other data like age and etc. Leaving it uncapped would also be bad as one high outlier may stretch
the median and other data. And lastly leaving it capped helps mostly with outliers and median while still keeping all Row 103 other data.

4. Explain why the median, not the mean, was used to fill in the missing sleep values. Use numbers from your own program. [2]
   Using median is safer than mean, because it uses the middle number from the sorted list, meaning it does not affect data 
as much as if you'd use mean which is average of all numbers.


5. Describe what would happen in a k-nearest neighbours model if the columns steps and sleep were not normalized. [2]


# Sort a Big File with one string per line (~20GB)

### Questions

1. What kind of data does the file store? ( Data with constraints eg. DNA sequences, can be sorted using Bucket Sort )
2. Where is the file ( Can be moved to a machine running faster hardware? )

# Procedure (External Sort)

1. Split the file into small chucks (eg. ~100MB each)
2. Sort each chuck
3. Merge the chucks together

# Improvements

1. Parralism
⋅⋅* put chuncks on different machines and sort individually.
⋅⋅* use multithreading 
⋅⋅* Asychronous Read/Write - While one chunck is being sorted, another can be merged.
⋅⋅* Use fast network links to parrallise

2. Hardware
⋅⋅* Use more RAM to hold more data in memory at a time
⋅⋅* Increase CPU speed and number of cores, RAM access latency, input/output bandwidth, disk read/write speed, disk seek time, and others.

2. Software
⋅⋅* Using Radix sort if possible
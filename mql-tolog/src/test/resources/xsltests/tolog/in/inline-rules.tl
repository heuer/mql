
composed-by($composer, $work) :- 
    composed-by($work: work, $composer: composer)
.

select $opera from composed-by(puccini, $opera)?

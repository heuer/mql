
created-by($composer, $work) :-
    composed-by($work: work, $composer: composer)
.

select $opera from created-by(puccini, $opera)?

SELECT
	gp.progarmme_id,   
	count(c.del) nums,
	sum(case when c.del=1 then 1 else 0 end) suc,
		sum(case when c.del=0 then 1 else 0 end) fail
FROM
	gv_cycleQueue c
LEFT JOIN gv_publish p ON c.rltRecID = p.publish_id
LEFT JOIN gv_programme gp ON gp.progarmme_id = p.progarmme_id
LEFT JOIN gv_equipment e ON e.equipment_id = p.equipment_id
LEFT JOIN t_mb_group g ON e.depart_id = g.groupid

WHERE
	e.equipment_id IS NOT NULL
group by gp.progarmme_id 
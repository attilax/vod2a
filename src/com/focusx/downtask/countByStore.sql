 WITH query AS ( SELECT TOP 1000 
  rec.ds_id,rec.prgrmid,prg.describe,rec.trueDownFlag,
      rec.startdownFlag,
     rec.[material_id]
      ,rec.equipment_id
      ,rec.download_status,
      grp.parentid as areaId,
      grp.groupid as storeid,
      mt1.[material_description]
	  ,grp.groupid, mt1.play_time as mtTimeLen
  FROM [gv_download_task]  rec
  left join [gv_equipment] eq on rec.equipment_id=eq.equipment_id
  left join [t_mb_group] grp on grp.[groupid]=eq.depart_id
  left join gv_material mt1 on rec.material_id = mt1.material_id
    left join gv_programme prg on prg.progarmme_id=rec.prgrmID
     where   1=1  @where
 
  
   )
 --  select * from query
   ,q4brch as
   (
   SELECT query.*,grp5.parentid as branchId from query 
     left join  t_mb_group grp5 on grp5.[groupid]= query.areaId
   
   )
  -- select * from q4brch
   
, query2 AS (
   SELECT  *
   --q4brch.material_id,storeid, count( *) as shouldDown, sum([download_status]) actDown
   
     FROM q4brch   
   
--	group by storeid,q4brch.material_id 
	)
	
--	select * from query2
---mt.[material_description],  comm on ob8
	,q4fmt as 
	(
	select query2.*,grp4.groupname,grp5.groupname as storename,grp6.groupname as areaname,ROW_NUMBER() OVER (order by grp4.groupname  ) as rownum
	  from query2  
	 
	    left join t_mb_group grp5   on grp5.[groupid]= query2.storeid	    
	      left join t_mb_group grp6   on grp6.[groupid]= grp5.parentId
	       left join t_mb_group grp4   on grp4.[groupid]=  grp6.parentId
	 left join gv_material mt on query2.material_id = mt.material_id
	)
	
	select * from  q4fmt where storename is not null order by ds_id desc

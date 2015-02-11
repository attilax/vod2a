 WITH query AS ( SELECT   [pr_id]
      ,[screen]
      ,rec.[play_time]
      ,[publish_type]
      ,rec.[material_id]
      ,rec.equipment_id
      ,[programme_id]
	  ,grp.groupid, mt1.play_time as mtTimeLen
  FROM [gialweixin].[dbo].[gv_play_record] rec
  left join [gv_equipment] eq on rec.equipment_id=eq.equipment_id
  left join [t_mb_group] grp on grp.[groupid]=eq.depart_id
  left join gv_material mt1 on rec.material_id = mt1.material_id
     where   1=1 @where
 
  
   )
, query2 AS (
   SELECT query.material_id,grp1.parentId as areaId, count(distinct query.groupid) as groupid_count,
   count(query.material_id) as  playCount,SUM(mtTimeLen) as play_time_sum
     FROM query  
    left join  t_mb_group grp1 on grp1.[groupid]= query.groupid
   
	group by grp1.parentId,query.material_id 
	)
	
	, q3 AS (
	select query2.*,grp4prov.groupname as provname,mt.[material_description],grp4prov.parentId as subcomId
	 from query2 left join t_mb_group grp4prov   on grp4prov.[groupid]= query2.areaId
	 left join gv_material mt on query2.material_id = mt.material_id
	 )
	 
	 select top 999  *,grp4subcom.groupname as subcom,ROW_NUMBER() OVER (order by grp4subcom.groupname  ) as rownum from q3   
	 left join t_mb_group grp4subcom   on grp4subcom.[groupid]= q3.subcomId 
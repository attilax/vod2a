SELECT TOP 1000 [progarmme_id]
      ,[describe]
      ,[screen]
      ,[total_duration]
      ,[material_number]
      ,[create_man]
      ,[create_time]
  FROM [gialweixin].[dbo].[gv_programme] m
  left join [review] r on m.[progarmme_id]=r.targetid  and  r.targettype='prgrm'
 where 1=1 @where
  order by [progarmme_id] desc
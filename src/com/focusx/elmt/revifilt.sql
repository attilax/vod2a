/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP 1000 [material_id]
      ,[material_description]
      ,[material_type]
      ,[create_user]
      ,[create_time]
      ,[effectie_time]
      ,[failure_time]
      ,[size]
      ,[file_path]
      ,[can_down_org]
      ,[update_user]
      ,[update_time]
      ,[application_type]
      ,[play_time]
      ,[logicDel],r.*
  FROM [gialweixin].[dbo].[gv_material] m
  left join [review] r on m.[material_id]=r.targetid  and  r.targettype='mtrl'
 where 1=1 @where
  order by [material_id] desc
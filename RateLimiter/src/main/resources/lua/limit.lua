local key = KEYS[1]
local maxCount = tonumber(ARGV[1])
local time = tonumber(ARGV[2])
local count = redis.call('get',key)
if(count and tonumber(count) > maxCount) then
    return tonumber(count)
end

count = redis.call('incr',key)
if(count == 1) then
    redis.call('expire',key,time)
end
return count